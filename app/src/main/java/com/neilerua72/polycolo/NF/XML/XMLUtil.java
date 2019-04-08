package com.neilerua72.polycolo.NF.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * A utility class that facilitates the manipulation
 * of DOM document, including the evaluation of XPath.
 *
 * Beware: xmlns (default namespace) can not be specified.
 *
 * See inner classes for more information.
 *
 * To simplify usage all methods are static and are declared to throw
 * an indistinct Exception.
 *
 * @author (Copyright) 2011-2018 Emmanuel Promayon, Université Grenoble Alpes - TIMC-IMAG
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
public class XMLUtil {

    /** Inner class to evaluate XPath expression on a DOM Document
     */
    static public class XPathEvaluateExpression {

        /** To use when the XPath evaluation is expected to return a text content.
         *
         * @param xpathExpression the xpath expression to evaluate
         * @param doc the DOM Document to use for the expression
         * @return the resulting evaluation as a String
         * @throws java.lang.Exception
         */
        public static String getString(String xpathExpression, Document doc) throws Exception {
            // Factory Instanciation
            XPathFactory factory = XPathFactory.newInstance();
            // Create a new XPath object
            XPath xpath = factory.newXPath();
            // Compilation of the expression (String -> XPath)
            XPathExpression expression = xpath.compile(xpathExpression);
            // Evaluation of the XPath expression on doc (only first text answer is collected and returned)
            return expression.evaluate(doc);
        }


        /** To use when the XPath evaluation is expected to return a number.
         *
         * @param xpathExpression the xpath expression to evaluate
         * @param doc the DOM Document to use for the expression
         * @return the resulting evaluation as a number (double)
         * @throws java.lang.Exception

        public static double getNumber(String xpathExpression, Document doc) throws Exception {
        // Factory Instanciation
        XPathFactory factory = XPathFactory.newInstance();
        // Create a new XPath object
        XPath xpath = factory.newXPath();
        // Compilation of the expression (String -> XPath)
        XPathExpression expression = xpath.compile(xpathExpression);
        // Evaluation of the XPath expression on doc (only first text answer is collected and returned)
        return (Double) expression.evaluate(doc, XPathConstants.NUMBER);
        }
         */

        /** To use when the XPath evaluation is expected to return a list of nodes
         *
         * @param xpathExpression the xpath expression to evaluate
         * @param doc the DOM Document to use for the expression
         * @return the resulting evaluation as a DOM list of nodes
         * @throws java.lang.Exception
         */
        public static NodeList getNodeList(String xpathExpression, Document doc) throws Exception {
            // Factory Instanciation
            XPathFactory factory = XPathFactory.newInstance();
            // Create a new XPath object
            XPath xpath = factory.newXPath();
            // Compilation of the expression (String -> XPath)
            XPathExpression expression = xpath.compile(xpathExpression);
            // Evaluation of the XPath expression on doc
            return (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        }
    }

    /** Inner class to create DOM document with various methods
     *  including URL, XSL transformation and files.
     */
    static public class DocumentFactory {

        /** build a DOM Document from a String: this methods parse a String
         *  that should represent an XML serialisation.
         *
         * @param xmlString the XML document as a String
         * @return the DOM Document build from the XML document.
         * @throws java.lang.Exception
         */
        static public Document fromString(String xmlString) throws Exception {
            // initialize DOM context
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true); // never forget this!

            // build the DOM document from the String
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }


        /** return the DOM document resulting from of an XSL transformation of a source
         *  DOM document
         *
         * @param xslFileName name of the file containing the XSL stylesheet
         * @param source the DOM document to process by XSLT
         * @return the DOM document resulting from the XSL transformation of source as written in the stylesheet xslFileName
         * @throws java.lang.Exception
         */
        public static Document fromXSLTransformation(String xslFileName, Document source) throws Exception {
            // initialize DOM context
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true); // never forget this!

            // build the DOM document from the String
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document output = builder.newDocument();
            Result result = new DOMResult(output);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer t = transformerFactory.newTransformer(new StreamSource(xslFileName));
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.transform(new DOMSource(source), result);
            return output;
        }

        /** build a DOM Document from an XML file.
         *
         * @param fileName name of the input file (have to be XML)
         * @return the corresponding DOM Document
         * @throws java.lang.Exception
         */
        public static Document fromFile(String fileName) throws Exception {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document theDocument = db.parse(new File(fileName));
            return theDocument;
        }
    }

    /** write a DOM Document in a file
     *
     * @param doc the DOM Document to serialize in a file
     * @param outputFileName the file name to write to
     * @throws java.lang.Exception
     */
    public static void writeDoc(Document doc, String outputFileName) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        DocumentType dt = doc.getDoctype();
        if (dt != null) {
            String pub = dt.getPublicId();
            if (pub != null) {
                t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, pub);
            }
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dt.getSystemId());
        }
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // NOI18N

        t.setOutputProperty(OutputKeys.INDENT, "yes"); // NOI18N

        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // NOI18N

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new FileOutputStream(outputFileName));
        t.transform(source, result);

    }
}