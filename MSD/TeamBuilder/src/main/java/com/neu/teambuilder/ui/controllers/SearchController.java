/**
 * 
 */
package com.neu.teambuilder.ui.controllers;

import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;

import com.neu.teambuilder.bol.businessObjects.Article;
import com.neu.teambuilder.bol.businessObjects.AuthorQuery;
import com.neu.teambuilder.bol.businessObjects.Bucket;
import com.neu.teambuilder.bol.businessObjects.Filter;
import com.neu.teambuilder.bol.businessObjects.FilterType;
import com.neu.teambuilder.bol.businessObjects.Person;
import com.neu.teambuilder.bol.businessObjects.User;
import com.neu.teambuilder.resources.Util;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author ideepakkrishnan
 *
 */
public class SearchController implements Initializable {
	
	@FXML
	private VBox selectionPane;
	
	@FXML
	private TabPane openSearches;
	
	@FXML
	private Button btnAddJournalName;
	
	@FXML
	private Button btnAddConfName;
	
	@FXML
	private Button btnAddBookName;
	
	@FXML
	private Button btnAddThesisTitle;
	
	@FXML
	private Button btnAddPaperTitle;
	
	@FXML
	private Button btnAddFrom;
	
	@FXML
	private Button btnAddTo;
	
	@FXML
	private Button btnAddAuthorName;
	
	@FXML
	private Button btnAddMinPublications;
	
	@FXML
	private Button btnAddMinCitations;
	
	@FXML
	private Button btnAddMinYearsServed;
	
	@FXML
	private Button btnAddFOE;
	
	@FXML
	private Button btnAddStartSet;
	
	@FXML
	private Button btnAddEndSet;
	
	@FXML
	private Button btnAddAnd;
	
	@FXML
	private Button btnAddOr;
	
	@FXML
	private GridPane contentContainer;
	
	@FXML
	private Label lblAuthorName;
	
	@FXML
	private Label lblAlias;
	
	@FXML
	private Label lblHomePage;
	
	@FXML
	private Label lblFOE;
	
	@FXML
	private ImageView authorImage;
	
	@FXML
	private TableView<Article> articlesContainer;
	
	@FXML
	private HiddenSidesPane pane;
    
    @FXML
    private Label pinLabel;
    
    @FXML
    private Label lblTotalPublications;
    
    @FXML
    private Label lblRecentPublications;
    
    @FXML
    private Label lblTotalCitations;
    
    @FXML
    private Label lblRecentCitations;
    
	private PopOver editor;
	private Button btnSourceFilter;
	private TextField input = null;
	private DatePicker dateVal = null;
	private ToggleButton tbOpt1 = null;
	private ToggleButton tbOpt2 = null;
	private ToggleGroup combiner = null;
	private Tab activeTab = null;
	private FlowPane activeTabFilterContainer = null;
	private TableView<Person> activeTabResultTable = null;
	private static HashMap<Integer, AuthorQuery> hmTabQuery = 
			new HashMap<Integer, AuthorQuery>();
	
	// TODO Remove this after adding session handlers
	private User currUser = new User();
	private Person selectedAuthor;
	
	@FXML
	private void handleNewSearch(ActionEvent event) {
		// Container to hold asserted filters
		FlowPane appliedFilterContainer = addActiveFilterContainer();
		
		// ToolBar showing available actions
		ToolBar searchOptions = addActionsToolBar();
		
		// Container to display search results
		TableView<Person> searchResultsContainer = 
				addSearchResultContainer(
						(Button) searchOptions.getItems().get(1),
						(Button) searchOptions.getItems().get(2));
		
		// Insert above child controls into a grid
		GridPane tabContentGrid = new GridPane();
		tabContentGrid.add(appliedFilterContainer, 0, 0);
		tabContentGrid.add(searchOptions, 0, 1);
		tabContentGrid.setHgrow(searchOptions, Priority.SOMETIMES);
		tabContentGrid.add(searchResultsContainer, 0, 2);
		tabContentGrid.setHgrow(searchResultsContainer, Priority.SOMETIMES);
		tabContentGrid.setVgrow(searchResultsContainer, Priority.SOMETIMES);
		
		Tab newSearchTab = new Tab();
		newSearchTab.setText("New Search");
		newSearchTab.setContent(tabContentGrid);
		openSearches.getTabs().add(newSearchTab);
		openSearches.getSelectionModel().select(newSearchTab);
	}
	
	private ToolBar addActionsToolBar() {
		
		Button btnSearch = new Button("Search");
		btnSearch.setOnAction(searchEventHandler);
		
		Button btnViewSelectedAuthorInfo = new Button("View Author Details");
		btnViewSelectedAuthorInfo.setOnAction(viewSelectedAuthorInfoEvent);
		btnViewSelectedAuthorInfo.setVisible(false);
		
		Button btnAddSelectedAuthorsToBucket = new Button("Add to Committee");
		btnAddSelectedAuthorsToBucket.setOnAction(
				addSelectedAuthorsToBucketEventHandler);
		btnAddSelectedAuthorsToBucket.setVisible(false);
		
		ToolBar searchOptions = new ToolBar();
		searchOptions.getItems().add(btnSearch);
		searchOptions.getItems().add(btnViewSelectedAuthorInfo);
		searchOptions.getItems().add(btnAddSelectedAuthorsToBucket);
		
		return searchOptions;
		
	}
	
	private TableView<Person> addSearchResultContainer(
			Button btnViewSelectedAuthorInfo,
			Button btnAddSelectedAuthorsToBucket) {
		
		// Variable storing search results
		ObservableList<Person> searchResultsList = null;
		
		// Container to display search results
		TableView<Person> searchResultsContainer = new TableView<Person>();
		searchResultsContainer.setItems(searchResultsList);
		
		TableColumn<Person, String> colName = new TableColumn<Person, String>("Name");
		colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		colName.setSortType(SortType.ASCENDING);
		
		TableColumn<Person, Integer> colPubCount = new TableColumn<Person, Integer>("Publication Count");
		colPubCount.setCellValueFactory(new PropertyValueFactory<Person, Integer>("publicationCount"));
		
		TableColumn<Person, Integer> colCiteCount = new TableColumn<Person, Integer>("Citation Count");
		colCiteCount.setCellValueFactory(new PropertyValueFactory<Person, Integer>("citationCount"));
		
		TableColumn<Person, Integer> colRanking = new TableColumn<Person, Integer>("Ranking");
		colRanking.setCellValueFactory(new PropertyValueFactory<Person, Integer>("ranking"));
		colRanking.setCellFactory(new Callback<TableColumn<Person,Integer>, TableCell<Person,Integer>>() {
			
			@Override
			public TableCell<Person, Integer> call(TableColumn<Person, Integer> param) {
				return new TableCell<Person, Integer>() {
					final GridPane ratingContainer;
					final Rating rating;
					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						
						rating = new Rating(5);
						rating.setScaleX(0.5);
						rating.setScaleY(0.5);
						rating.setPadding(new Insets(-12, 0, 0, -60));
						rating.setDisable(true);
						
						ratingContainer = new GridPane();
						ratingContainer.setMinHeight(15);
						ratingContainer.setMaxHeight(15);
						ratingContainer.setPrefHeight(15);
						ratingContainer.add(rating, 0, 0);
					}
					
					//Display rating if the row is not empty
					@Override
					protected void updateItem(Integer value, boolean empty) {
						super.updateItem(value, empty);
						
						if (empty || value == null) {
							setGraphic(null);
						} else {
							rating.setRating(value);
							setGraphic(ratingContainer);
						}
					}
				};
			}
		});
		colRanking.setSortType(SortType.DESCENDING);
		
		searchResultsContainer.getColumns().setAll(
				colName,
				colPubCount,
				colCiteCount,
				colRanking);
		
		searchResultsContainer.getSortOrder().addAll(colRanking, colName);
		searchResultsContainer.setContextMenu(createTableContextMenu());
		searchResultsContainer.getSelectionModel()
							  .setSelectionMode(SelectionMode.MULTIPLE);
		
		searchResultsContainer.getSelectionModel()
							  .selectedItemProperty()
							  .addListener((obs, oldSelection, newSelection) -> {
								  if (newSelection != null) {
									  btnViewSelectedAuthorInfo.setVisible(true);
									  btnAddSelectedAuthorsToBucket.setVisible(true);
								  } else {
									  btnViewSelectedAuthorInfo.setVisible(false);
									  btnAddSelectedAuthorsToBucket.setVisible(false);
								  }
							  });
		
		return searchResultsContainer;
		
	}
	
	private FlowPane addActiveFilterContainer() {
		FlowPane appliedFilterContainer = new FlowPane();
		appliedFilterContainer.setMinHeight(100);
		appliedFilterContainer.setMaxHeight(100);
		appliedFilterContainer.setPrefHeight(100);
		
		return appliedFilterContainer;
	}
	
	@FXML
	private void handleNewFilterAction(ActionEvent event) {
		
		editor = new PopOver();
		
		btnSourceFilter = (Button)event.getSource();
		Boolean isSourceDateFilter = btnSourceFilter.getStyleClass().contains("date-filter");
		Boolean isJoinFilter = btnSourceFilter.getStyleClass().contains("conditional-filter");
		
		if (!isJoinFilter) {
			if (isSourceDateFilter) {
				dateVal = new DatePicker();
			} else {
				input = new TextField();
			}
			
			Button btnPositiveResponse = createAddButton();		
			Button btnNegativeResponse = createCancelButton();
			
			Insets contentMargin = new Insets(10, 10, 10, 10);
			
			GridPane editControlsGrid = new GridPane();
			editControlsGrid.setHgap(5);
			editControlsGrid.setVgap(5);
			
			if (isSourceDateFilter) {
				editControlsGrid.add(dateVal, 0, 0);
			} else {
				editControlsGrid.add(input, 0, 0);
			}
			
			editControlsGrid.add(btnPositiveResponse, 1, 0);
			editControlsGrid.add(btnNegativeResponse, 2, 0);
			editControlsGrid.setPadding(contentMargin);
			
	        editor.setContentNode(editControlsGrid);
	        editor.show(btnSourceFilter);
		} else {
			// Add join filter to active filter list
			Button btnNewFilter = addSearchFilter(btnSourceFilter.getId(), "");
			btnNewFilter.setOnAction(editEventHandler);
			
			intializeActiveTabControlHandles();
			
			activeTabFilterContainer.setMargin(btnNewFilter, new Insets(5));
			activeTabFilterContainer.getChildren().add(btnNewFilter);
		}
        
    }
	
	final EventHandler<ActionEvent> cancelEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {			
			editor.hide();
			editor = null;
		}
		
	};
	
	final EventHandler<ActionEvent> addEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			String content = "";
			
			if (input != null) {
				content = URLEncoder.encode(input.getText().trim());
			} else if (dateVal != null) {
				content = dateVal.getValue().toString();
			}
			
			if (content != null && content != "" && content.length() > 0) {
				Tab openTab = null;
				ObservableList<Tab> tabs = openSearches.getTabs();
				for (Tab t : tabs) {
					if (t.isSelected()) {
						openTab = t;
						break;
					}
				}
				
				if (openTab != null) {
					System.out.println("Open Tab: " + openTab.getText());
				}
				
				// Add the filter to active filter list
				Button btnNewFilter = addSearchFilter(btnSourceFilter.getId(), content);
				btnNewFilter.setOnAction(editEventHandler);
				
				intializeActiveTabControlHandles();
				
				activeTabFilterContainer.setMargin(btnNewFilter, new Insets(5));
				activeTabFilterContainer.getChildren().add(btnNewFilter);
				
				// Close the pop over control
				editor.hide();
				editor = null;
				
				// Reset input controls in pop over control
				input = null;
				dateVal = null;
			}
		}
	};
	
	final EventHandler<ActionEvent> editEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {			
			editor = new PopOver();
			
			btnSourceFilter = (Button)event.getSource();
			String[] content = btnSourceFilter.getText().split(" : ");
			Boolean isSourceDateFilter = btnSourceFilter.getStyleClass().contains("date-filter");
			Boolean isJoinFilter = btnSourceFilter.getStyleClass().contains("conditional-filter");
			GridPane gpCombiner = null;
			
			if (isSourceDateFilter) {
				dateVal = new DatePicker(LocalDate.parse(content[1]));
			} else if (isJoinFilter) {				
				combiner = new ToggleGroup();
				tbOpt1 = new ToggleButton();
				tbOpt1.setToggleGroup(combiner);
				tbOpt2 = new ToggleButton();
				tbOpt2.setToggleGroup(combiner);
				
				if (content[0].equals("AND") || content[0].equals("OR")) {
					tbOpt1.setText("AND");
					tbOpt1.setUserData("AND");
					tbOpt2.setText("OR");
					tbOpt2.setUserData("OR");
					
					if (content[0].equals("AND")) {
						tbOpt1.setSelected(true);
					} else {
						tbOpt2.setSelected(true);
					}
				} else {
					tbOpt1.setText("(");
					tbOpt1.setUserData("(");
					tbOpt2.setText(")");
					tbOpt2.setUserData(")");
					
					if (content[0].equals("(")) {
						tbOpt1.setSelected(true);
					} else {
						tbOpt2.setSelected(true);
					}
				}
				
				gpCombiner = new GridPane();
				gpCombiner.setHgap(5);
				gpCombiner.add(tbOpt1, 0, 0);
				gpCombiner.add(tbOpt2, 1, 0);
			} else {
				input = new TextField(content[1]);
			}
			
			Button btnPositiveResponse = createUpdateButton();
			Button btnRemoveRespose = createRemoveButton();
			Button btnNegativeResponse = createCancelButton();
			
			Insets contentMargin = new Insets(10, 10, 10, 10);
			
			GridPane editControlsGrid = new GridPane();
			editControlsGrid.setHgap(5);
			editControlsGrid.setVgap(5);
			
			if (isSourceDateFilter) {
				editControlsGrid.add(dateVal, 0, 0);
			} else if (isJoinFilter) {
				editControlsGrid.add(gpCombiner, 0, 0);
			} else {
				editControlsGrid.add(input, 0, 0);
			}
			
			editControlsGrid.add(btnPositiveResponse, 1, 0);
			editControlsGrid.add(btnRemoveRespose, 2, 0);
			editControlsGrid.add(btnNegativeResponse, 3, 0);
			editControlsGrid.setPadding(contentMargin);
			
	        editor.setContentNode(editControlsGrid);
	        editor.show(btnSourceFilter);
		}
		
	};
	
	final EventHandler<ActionEvent> updateEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			String content = "";
			
			if (input != null) {
				content = URLEncoder.encode(input.getText().trim());
			} else if (dateVal != null) {
				content = dateVal.getValue().toString();
			} else if (combiner != null) {
				content = combiner.getSelectedToggle().getUserData().toString();
			}
			
			if (content != null && content != "" && content.length() > 0) {
				String[] oldContent = btnSourceFilter.getText().split(" : ");
				String newContent = "";
				
				if (oldContent[0].equals("AND") || oldContent[0].equals("OR") || 
						oldContent[0].equals("(") || oldContent[0].equals(")")) {
					newContent = content;
				} else {
					newContent = oldContent[0] + " : " + content;
				}
				
				btnSourceFilter.setText(newContent);
				
				// Close the pop over control
				editor.hide();
				editor = null;
				
				// Reset input controls in pop over control
				input = null;
				dateVal = null;
			}
		}
	};
	
	final EventHandler<ActionEvent> removeEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if (editor != null) {
				editor.hide();
				editor = null;
			}
			
			FlowPane parent = (FlowPane) btnSourceFilter.getParent();
			parent.getChildren().remove(btnSourceFilter);
			btnSourceFilter = null;
		}
	};
	
	final EventHandler<ActionEvent> searchEventHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// Parse through the active filter list and convert
			// them into Filter objects
			List<Filter> lstActiveFilters = new ArrayList<Filter>();
			
			intializeActiveTabControlHandles();
			
			ObservableList<Node> olActiveFilters = activeTabFilterContainer.getChildren();
			for (Node n : olActiveFilters) {				
				Button btnFilter = null;
				
				if (n instanceof Button) {
					btnFilter = (Button) n;
				}
				
				if (btnFilter != null) {
					String[] contents = btnFilter.getText().split(" : ");
					String style = btnFilter.getStyleClass().get(1);
					
					Filter newFilter = new Filter();
					newFilter.setType(getFilterType(contents[0], style));
					if (contents.length > 1) {
						// Means this is not a join filter
						newFilter.setValue(contents[1]);
					}
					
					lstActiveFilters.add(newFilter);
				}
			}
			
			// Execute the query and fetch results
			AuthorQuery query = new AuthorQuery(currUser);
			query.setFilters(lstActiveFilters);
			ObservableList<Person> result = query.execute();
			
			intializeActiveTabControlHandles();
			
			// Save the current sort policy for the table
			List<TableColumn<Person, ?>> sortOrder = 
					new ArrayList<>(activeTabResultTable.getSortOrder());
			activeTabResultTable.getSortOrder().clear();
			
			// Load results into the table
			activeTabResultTable.setItems(result);
			
			// Apply the sorting policy to sort the loaded results
			activeTabResultTable.getSortOrder().addAll(sortOrder);
			
			// Map the query to active tab
			Tab activeTab = getActiveTab();
			hmTabQuery.put(activeTab.hashCode(), query);
		}
	};
	
	final EventHandler<ActionEvent> removeSelectedAuthorEventHandler = 
			new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {			
			Button author = (Button) event.getSource();
			currUser.getBucket().removeAuthor(author.getId());
			
			GridPane focused = (GridPane) author.getParent();
			VBox bucketContainer = (VBox) focused.getParent();
			bucketContainer.getChildren().remove(focused);
		}
	};
	
	final EventHandler<ActionEvent> addSelectedAuthorsToBucketEventHandler = 
			new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					handleAddSelectedAuthorsToBucket();
				}
			};
	
	final EventHandler<ActionEvent> viewSelectedAuthorInfoEvent = 
			new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					intializeActiveTabControlHandles();
					
					if (activeTabResultTable.getItems().size() > 0) {
						Person selected = activeTabResultTable
											.getSelectionModel()
											.getSelectedItem();
						
						initializeAuthorDetailsPane(selected);
						
						// Display the pane
						pane.setPinnedSide(Side.RIGHT);
					}					
				}
			};
			
	private Tab getActiveTab() {
		return openSearches.getSelectionModel().getSelectedItem();
	}
	
	private void intializeActiveTabControlHandles() {
		Tab currTab = getActiveTab();
		
		if (activeTab == null || activeTab != currTab) {
			activeTab = currTab;
			
			GridPane selectedTabContent = (GridPane) activeTab.getContent();
			
			activeTabFilterContainer = 
					(FlowPane) selectedTabContent.getChildren().get(0);
			
			activeTabResultTable = 
					(TableView<Person>) selectedTabContent.getChildren().get(2);
		}
				
	}
	
	private FilterType getFilterType(String prefix, String style) {
		FilterType type = FilterType.Unknown;
		
		switch(style) {
			case "publisher-filter":
				if (prefix.equals("J")) {
					type = FilterType.Journal;
				} else if (prefix.equals("C")) {
					type = FilterType.Conference;
				} else {
					type = FilterType.Book;
				}
				break;
			case "publication-filter":
				if (prefix.equals("T")) {
					type = FilterType.Thesis;
				} else {
					type = FilterType.Paper;
				}
				break;
			case "date-filter":
				if (prefix.equals("F")) {
					type = FilterType.FromDate;
				} else {
					type = FilterType.ToDate;
				}
				break;
			case "other-filter":
				if (prefix.equals("A")) {
					type = FilterType.AuthorName;
				} else if (prefix.equals("P")) {
					type = FilterType.MinPublicationCount;
				} else if (prefix.equals("C")) {
					type = FilterType.MinCitationCount;
				} else if (prefix.equals("Y")) {
					type = FilterType.MinExperienceAsOfficeBearer;
				} else {
					type = FilterType.FieldOfExperience;
				}
				break;
			case "conditional-filter":
				if (prefix.equals("(")) {
					type = FilterType.JoinStart;
				} else if (prefix.equals(")")) {
					type = FilterType.JoinEnd;
				} else if (prefix.equals("AND")) {
					type = FilterType.And;
				} else {
					type = FilterType.Or;
				}
				break;
		}
		
		return type;
	}
	
	private Button addSearchFilter(String source, String value) {
		String[] config = getFilterConfig(source);
		String content = config[0];
		if (config[1] != "conditional-filter") {
			content = content + " : " + value;
		}
		
		Button btnFilter = new Button();
		btnFilter.getStyleClass().add(config[1]);
		btnFilter.setText(content);
		
		return btnFilter;
	}
	
	private String[] getFilterConfig(String value) {
		String[] strConfig = new String[2];
		
		switch (value) {
			case "btnAddJournalName":
				strConfig[0] = "J";
				strConfig[1] = "publisher-filter";
				break;
			
			case "btnAddConfName":
				strConfig[0] = "C";
				strConfig[1] = "publisher-filter";
				break;
				
			case "btnAddBookName":
				strConfig[0] = "B";
				strConfig[1] = "publisher-filter";
				break;
				
			case "btnAddThesisTitle":
				strConfig[0] = "T";
				strConfig[1] = "publication-filter";
				break;
				
			case "btnAddPaperTitle":
				strConfig[0] = "P";
				strConfig[1] = "publication-filter";
				break;
				
			case "btnAddFrom":
				strConfig[0] = "F";
				strConfig[1] = "date-filter";
				break;
				
			case "btnAddTo":
				strConfig[0] = "T";
				strConfig[1] = "date-filter";
				break;
				
			case "btnAddAuthorName":
				strConfig[0] = "A";
				strConfig[1] = "other-filter";
				break;
				
			case "btnAddMinPublications":
				strConfig[0] = "P";
				strConfig[1] = "other-filter";
				break;
			
			case "btnAddMinCitations":
				strConfig[0] = "C";
				strConfig[1] = "other-filter";
				break;
				
			case "btnAddMinYearsServed":
				strConfig[0] = "Y";
				strConfig[1] = "other-filter";
				break;
				
			case "btnAddFOE":
				strConfig[0] = "F";
				strConfig[1] = "other-filter";
				break;
				
			case "btnAddStartSet":
				strConfig[0] = "(";
				strConfig[1] = "conditional-filter";
				break;
				
			case "btnAddEndSet":
				strConfig[0] = ")";
				strConfig[1] = "conditional-filter";
				break;
				
			case "btnAddAnd":
				strConfig[0] = "AND";
				strConfig[1] = "conditional-filter";
				break;
				
			case "btnAddOr":
				strConfig[0] = "OR";
				strConfig[1] = "conditional-filter";
				break;
	
			default:
				break;
		}
		
		return strConfig;
	}

	private Button createAddButton() {
		ImageView imgAdd = Util.createImageView(
				"file:./src/main/resources/fxml/add.png", 15, 15);
		return Util.createImageButton(addEventHandler, imgAdd, 15, 15);
	}
	
	private Button createCancelButton() {
		ImageView imgCancel = Util.createImageView(
				"file:./src/main/resources/fxml/delete.png", 15, 15);
		return Util.createImageButton(cancelEventHandler, imgCancel, 15, 15);
	}
	
	private Button createUpdateButton() {
		ImageView imgUpdate = Util.createImageView(
				"file:./src/main/resources/fxml/ok.png", 15, 15);
		return Util.createImageButton(updateEventHandler, imgUpdate, 15, 15);
	}
	
	private Button createRemoveButton() {
		ImageView imgRemove = Util.createImageView(
				"file:./src/main/resources/fxml/remove.png", 15, 15);
		return Util.createImageButton(removeEventHandler, imgRemove, 15, 15);
	}
	
	private Button createRemoveSelectedAuthorButton() {
		ImageView imgRemove = Util.createImageView(
				"file:./src/main/resources/fxml/remove.png", 12, 12);
		return Util.createImageButton(removeSelectedAuthorEventHandler, imgRemove, 12, 12);
	}
	
	private ContextMenu createTableContextMenu() {
		
		MenuItem addAuthor = new MenuItem("Add to Committee");
		addAuthor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleAddSelectedAuthorsToBucket();
			}
		});
		
		SeparatorMenuItem separator = new SeparatorMenuItem();
		
		MenuItem viewInfo = new MenuItem("View Information");
		viewInfo.setOnAction(viewSelectedAuthorInfoEvent);
		
		ContextMenu menu = new ContextMenu();
		menu.getItems().addAll(addAuthor, separator, viewInfo);
		
		return menu;
	}
	
	private void handleAddSelectedAuthorsToBucket() {
		intializeActiveTabControlHandles();
		
		if (activeTabResultTable.getItems().size() > 0) {
			ObservableList<Person> selected = 
					activeTabResultTable
						.getSelectionModel()
						.getSelectedItems();
			
			for (Person p : selected) {
				addSelectedAuthorToBucket(p);
			}
		}
	}
	
	private void addSelectedAuthorToBucket(Person author) {
		
		if (!isDuplicateAuthor(author)) {
			currUser.getBucket().addAuthor(author);
			
			ImageView imgAuthor = Util.createImageView(
					"file:./src/main/resources/fxml/unknown.png", 30, 30);
			
			Button btnName = new Button(author.getName());			
			btnName.getStyleClass().add("author-details-button");
			btnName.setOnAction(new EventHandler<ActionEvent>() {
	
				@Override
				public void handle(ActionEvent event) {
					// TODO Add code to open Author info page
					
				}
			});
			
			Button btnRemove = createRemoveSelectedAuthorButton();
			btnRemove.setId("remove" + author.getKey());
			
			GridPane authorContainer = new GridPane();
			authorContainer.setHgap(5);
			authorContainer.setVgap(5);
			authorContainer.add(imgAuthor, 0, 0);
			authorContainer.add(btnName, 1, 0);
			authorContainer.setHgrow(btnName, Priority.SOMETIMES);
			authorContainer.add(btnRemove, 2, 0);
			authorContainer.getStyleClass().add("selected-author-container");
			
			selectionPane.getChildren().add(authorContainer);
		}
	}
	
	private boolean isDuplicateAuthor(Person author) {
		
		List<Person> selectedAuthors = currUser.getBucket().getSelectedAuthors();
		if (selectedAuthors != null) {
			return selectedAuthors.contains(author);
		}
		
		return false;
	}
	
	private void initializeBucket() {
		List<Person> selectedAuthors = currUser.getBucket().getSelectedAuthors();
		
		for (Person author : selectedAuthors) {
			addSelectedAuthorToBucket(author);
		}
	}
	
	/**
	 * Author Info slide out - Event handlers
	 */
	
	private void initializeArticleContainer() {
		
		// Initialize controls
		TableColumn<Article, String> colTitle = 
				new TableColumn<Article, String>("Title");
		colTitle.setCellValueFactory(
				new PropertyValueFactory<Article, String>("title"));
		
		TableColumn<Article, String> colPub = 
				new TableColumn<Article, String>("Publication");
		colPub.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Article,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Article, String> param) {				
				return param.getValue().getPublication().titleProperty();
			}
		});
		
		TableColumn<Article, String> colPublisher = 
				new TableColumn<Article, String>("Publisher");
		colPublisher.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Article,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Article, String> param) {				
				return param.getValue().getPublication().getPublisher().titleProperty();
			}
		});
		
		TableColumn<Article, Integer> colCitations = 
				new TableColumn<Article, Integer>("Citations");
		colCitations.getStyleClass().add("center-aligned-column");
		colCitations.setCellValueFactory(
				new PropertyValueFactory<Article, Integer>("citations"));
		
		TableColumn<Article, Integer> colYear = 
				new TableColumn<Article, Integer>("Year");
		colYear.getStyleClass().add("center-aligned-column");
		colYear.setCellValueFactory(
				new PropertyValueFactory<Article, Integer>("year"));
		
		articlesContainer.getColumns().setAll(
				colTitle, colPub, colPublisher, colCitations, colYear);
		
		articlesContainer.setItems(selectedAuthor.getArticles());
		
		// Workaround to disable row selection in this table
		articlesContainer.getSelectionModel()
						 .selectedIndexProperty()
						 .addListener((obs, oldVal, newVal) -> {
							 Platform.runLater(() -> {
								 articlesContainer.getSelectionModel()
								 				  .clearSelection();
							 });
						 });
		
	}
	
	private String convertListToString(List<String> list) {
		StringBuilder sbAliases = new StringBuilder();
		for (String alias : list) {
			if (sbAliases.length() > 0) {
				sbAliases.append("; ");
			}
			
			sbAliases.append(alias);
		}
		
		return sbAliases.toString();
	}
	
	@FXML
	private void handleGoBackAction(ActionEvent event) throws Exception {
		hideSlideOut();
	}
	
	@FXML
	private void handleAddSelectedAuthorAction(ActionEvent event) {
		intializeActiveTabControlHandles();
		addSelectedAuthorToBucket(this.selectedAuthor);
		
		// Close the slide out and reset author variable
		hideSlideOut();
	}
	
	private void hideSlideOut() {
		pane.setPinnedSide(null);
		this.selectedAuthor = null;
	}
	
	@FXML
	private void handleSearchSimilarAuthorsAction(ActionEvent event) {
		// TODO Handle similar author search action
	}
	
	private void initializeAuthorDetailsPane(Person author) {
		this.selectedAuthor = author;
		
		// Initialize the value for all fields in Author info page
		lblAuthorName.setText(this.selectedAuthor.getName());
		lblAlias.setText(convertListToString(this.selectedAuthor.getAliases()));
		lblHomePage.setText(this.selectedAuthor.getHomePageUrl());
		
		if (this.selectedAuthor.getImageUrl() != null) {
			String imageUrl = this.selectedAuthor.getImageUrl();
			
			if (imageUrl.length() > 0) {
				Image dp = new Image(imageUrl);
				authorImage.setImage(dp);
			}
		}
		
		// Load articles into table
		initializeArticleContainer();
		
	}
	
	/**
	 * Search page initialize method
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Remove the following line after adding session management
		currUser.setBucket(new Bucket(currUser));
		currUser.getBucket().setSelectedAuthors(new ArrayList<Person>());
		
		// Load all selected authors for current user
		initializeBucket();
	}

}