/*
 * Copyright 2018 David Wemhoener, Marc Liberatore.
 */

package index;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import documents.DocumentId;

public class SearchTool {
	
	public static void main(String[] args) throws IOException {
		
		SearchEngine searchEngine = new SearchEngine();
		


		/*
		 * Text below taken from Wikipedia on 2016-11-02, used under CC BY-SA 3.0 Unported License.
		 */
		String DOCUMENT1 = "one nation in the world one";
		DocumentId DOCUMENT1_ID = new DocumentId("asdf567");
		String DOCUMENT2 = "in the beginning";
		DocumentId DOCUMENT2_ID = new DocumentId("sdfg74567");
		String DOCUMENT3 = "nation and beginning are one";
		DocumentId DOCUMENT3_ID = new DocumentId("fdgh456754");
		String DOCUMENT4 = "nation world beginning house";
		DocumentId DOCUMENT4_ID = new DocumentId("fghj345634");
		String PIZZA = "The banana is an edible fruit, botanically a berry,[1][2] produced by several kinds of large herbaceous flowering plants in the genus Musa.[3] In some countries, bananas used for cooking may be called plantains. The fruit is variable in size, color and firmness, but is usually elongated and curved, with soft flesh rich in starch covered with a rind which may be green, yellow, red, purple, or brown when ripe. The fruits grow in clusters hanging from the top of the plant. Almost all modern edible parthenocarpic (seedless) bananas come from two wild species â€“ Musa acuminata and Musa balbisiana. The scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa Ã— paradisiaca for the hybrid Musa acuminata Ã— M. balbisiana, depending on their genomic constitution. The old scientific name Musa sapientum is no longer used.";
		DocumentId PIZZA_ID = new DocumentId("qwer85678");
		String SPAGHETTI = "A plantain(/ËˆplÃ¦ntáµ»n/,[1][2] US /plÃ¦nËˆteÉªn/,[3][4] UK /ËˆplÉ‘Ë�ntáµ»n/[1]), or cooking plantain, is one of the less-sweet cultivars of the genus Musa, whose fruit is also known as a banana. Plantains are mostly eaten cooked, sometimes along with their leaves and peels, and are usually large, angular, and starchy (in contrast to \"common\" or \"dessert\" bananas, which are typically eaten raw and without the peel). Bananas are usually smaller, more rounded, and sweet, however there is no formal scientific distinction between plantains and bananas.";
		DocumentId SPAGHETTI_ID = new DocumentId("wert2345");
		String TOMATO_SAUCE = "A cream pie is a type of pie or cake filled with a rich custard or pudding that is made from milk, cream, flour, and eggs. It can come in many forms, including vanilla, lemon, lime, peanut butter, banana, coconut, and chocolate. A constant feature of all cream pies is the whipped cream topping. The custard filling is related to the French crÃ¨me patissiÃ¨re which is a key component of various French cakes and tarts. It is a one-crust pie. The crust may be a standard pie crust made with flour and lard, or one made from crumbled cookies or graham crackers.";
		DocumentId TOMATO_SAUCE_ID = new DocumentId("erty678567");
		String RATATOUILLE = "Bread pudding is made with stale bread and milk or cream, generally containing eggs, a form of fat such as oil, butter or suet, and depending on whether the pudding is sweet or savory, a variety of other ingredients. Sweet bread puddings may use sugar, syrup, honey, dried fruit, nuts, as well as spices such as cinnamon, nutmeg, mace, or vanilla. The bread is soaked in the liquids, mixed with the other ingredients, and baked.";
		DocumentId RATATOUILLE_ID = new DocumentId("rtyu2345");
		String PAELLA = "An apple pie is a fruit pie, in which the principal filling ingredient is apple. It is, on occasion, served with whipped cream or ice cream on top, or alongside cheddar cheese. The pastry is generally used top-and-bottom, making it a double-crust pie; the upper crust may be a circular or a pastry lattice woven of crosswise strips. Exceptions are deep-dish apple pie, with a top crust only, and open-face Tarte Tatin.";
		DocumentId PAELLA_ID = new DocumentId("tyui978");
		String FRIED_RICE = "PÃ¢tÃ© chinois (pronounced: [pÉ‘te Êƒinwa]) is a French Canadian dish similar to English cottage pie or shepherd's pie, or French hachis Parmentier. It is made from layered ground beef (sometimes mixed with sautÃ©ed diced onions) on the bottom layer, canned corn (either whole-kernel, creamed, or a mix) for the middle layer, and mashed potatoes on top. Variations may include sprinkling paprika on top of the potatoes, reversing the layering of ingredients, adding diced bell peppers to the ground beef, and serving the dish with pickled eggs or beets. PÃ¢tÃ© chinois (French for \"Chinese pie\") is often drizzled with ketchup once served.";
		DocumentId FRIED_RICE_ID = new DocumentId("yuio2345");
		String CHOW_MEIN = "Baked beans is a dish containing beans, sometimes baked but, despite the name, usually stewed, in a sauce.[1] Most commercially canned baked beans are made from haricot beans, also known as navy beans â€“ a variety of Phaseolus vulgaris in a sauce. In Ireland and the United Kingdom, a tomato and sugar sauce is most commonly used, and they are commonly eaten on toast or as part of a full English breakfast. American Boston baked beans use a sauce prepared with molasses and salt pork, the popularity of which has led to the city being nicknamed \"Beantown\".[2] Beans in a tomato and brown sugar, sugar or corn syrup sauce are a widely available type throughout the U.S. Canada's Quebec-style beans often use maple syrup. This style is also popular in states bordering Canada's Eastern provinces.";
		DocumentId CHOW_MEIN_ID = new DocumentId("zxcv87658");
		String WAT = "The Indonesian rijsttafel (Dutch pronunciation: [ËˆrÉ›isttaË�fÉ™l]), a Dutch word that literally translates to \"rice table\", is an elaborate meal adapted by the Dutch following the hidang presentation of Nasi Padang from the Padang region of West Sumatra.[1] It consists of many (forty is not an unusual number) side dishes served in small portions, accompanied by rice prepared in several different ways. Popular side dishes include egg rolls, sambals, satay, fish, fruit, vegetables, pickles, and nuts. In most areas where it is served, such as the Netherlands, and other areas of heavy Dutch influence (such as parts of the West Indies), it is known under its Dutch name.";
		DocumentId WAT_ID = new DocumentId("xcvb5324");
		
		searchEngine.addDocument(PIZZA_ID, new StringReader(PIZZA));
		searchEngine.addDocument(SPAGHETTI_ID, new StringReader(SPAGHETTI));
		searchEngine.addDocument(TOMATO_SAUCE_ID, new StringReader(TOMATO_SAUCE));
		searchEngine.addDocument(RATATOUILLE_ID, new StringReader(RATATOUILLE));
		searchEngine.addDocument(PAELLA_ID, new StringReader(PAELLA));
		searchEngine.addDocument(FRIED_RICE_ID, new StringReader(FRIED_RICE));
		searchEngine.addDocument(CHOW_MEIN_ID, new StringReader(CHOW_MEIN));
		searchEngine.addDocument(WAT_ID, new StringReader(WAT));
		
		Scanner scanner = new Scanner(System.in);
		String term = scanner.next();
		List<DocumentId> results = searchEngine.relevanceLookup(term,5);
		scanner.close();
		
		for(DocumentId result: results) System.out.println(result);
		
	}
	

}
