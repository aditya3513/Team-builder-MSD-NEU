package try_code;

import java.util.ArrayList;

public class break_keys {
	
	// function to get the domain from article
			public ArrayList<String> getDomains()
			{
				// here i am using dummy values in a string arraylist
				// made a query and added the functions and 5 values and then generated the query as a whole
				ArrayList<String> keys = new ArrayList<String>();
				keys.add("journals/cad/LasemiXG10");
				keys.add("journals/cad/MehradXG14");
				keys.add("journals/cad/XueY04");
				keys.add("journals/cii/XueSN01");
				keys.add("journals/cor/LiNXT11");
				String[] str;
				ArrayList<String> domain = new ArrayList<String>();
				for(int i=0; i<keys.size(); i++)
				{
					str = keys.get(i).split("/");
					String s = str[0]+"/"+str[1];
					if(!domain.contains(s))
					{
					domain.add(s);
					}
					
				}
				return domain;
			}
			
			/* now i have find max and min year function that takes articles and gives me max and min year
			 * for a person with his all articles list
			 * suppose they are stored in ----->  max and min
			 */
			public String similarAuthorQueries(ArrayList<String> domains, int maxYear, int minYear, int total_count)
			{
				String str;
				int max_count=total_count+50, min_count=total_count-50;
				StringBuilder query = new StringBuilder("");
				str = "select * from adi_xml_author where `key` in (select `key` from adi_xml_article where year > "
						+ minYear +" and year < "+ maxYear +" and (";
				query.append(str);
				
				for(int i=0; i<domains.size(); i++)
				{			
					if(i == 0)
					{
						query.append("`key` like '"+domains.get(i)+"%'");
					}
					else
					{
						query.append(" or `key` like '"+domains.get(i)+"%'");
					}
				}
				query.append(")and `key` in (select `key` from result_count where total_count between ");
				query.append(min_count+" and "+max_count);
				query.append("));");
				return query.toString();
			}
			
			
			
			public static void main(String[] args)
			{
				break_keys b = new break_keys();
				ArrayList<String> domains = b.getDomains();
				
				for(int i=0; i<domains.size(); i++)
				{
					System.out.println(domains.get(i));
				}
				
				String queries = b.similarAuthorQueries(domains, 2010, 2000,50);
				
					System.out.println(queries);
				
				
				
			}
			
		
			

}
