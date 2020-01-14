package TestDome.folders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hard, 20mins:
 *
 * Implement a function folderNames, which accepts a string containing an XML file that specifies folder structure
 * and returns all folder names that start with startingLetter. The XML format is given in the example below.

 For example, for the letter 'u' and an XML file:

 <?xml version="1.0" encoding="UTF-8"?>
 <folder name="c">
 <folder name="program files">
 <folder name="uninstall information" />
 </folder>
 <folder name="users" />
 </folder>
 the function should return a collection with items "uninstall information" and "users" (in any order).

 Also tried:

 String namePattern = "(name=\".+?\")";
 //        Pattern pattern = Pattern.compile(namePattern);
 //        Matcher matcher = pattern.matcher(xml); // 10mins
 */
public class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {

        if (xml == null || xml.length() == 0) {
            return Collections.emptyList();
        }

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xml));
        Document document = documentBuilder.parse(inputSource);
        NodeList nodeList = document.getElementsByTagName("folder");

        Map<Character, Collection<String>> startLetterToFolderNames = new HashMap<Character, Collection<String>>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Element.class.isAssignableFrom(node.getClass())) {
                Element element = (Element) node;
                String folderName = element.getAttribute("name");
                Character foundStartLetter = folderName.charAt(0);
                if (!startLetterToFolderNames.containsKey(foundStartLetter)) {
                    startLetterToFolderNames.put(foundStartLetter, new ArrayList<String>());
                }
                startLetterToFolderNames.get(foundStartLetter).add(folderName);
            }
        }

        if (startLetterToFolderNames.containsKey(startingLetter)) {
            return startLetterToFolderNames.get(startingLetter);
        }

        return Collections.emptyList();
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}
