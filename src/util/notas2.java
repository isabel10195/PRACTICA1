package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class notas2 
{
	/*private static final String FILENAME = "C:\\Users\\Isabel\\Desktop\\XML\\notas.xml";*/

	  public static void main(String[] args) 
	  {
		  String FILENAME="C:\\Users\\Isabel\\Desktop\\XML\\";
		 
		  Scanner sc= new Scanner(System.in);
		  System.out.println("Introduzca el fichero");
		  String file=sc.next();
		  FILENAME=FILENAME+file+ ".xml" ;
		  
	      // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try 
	      {
	          // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(FILENAME));

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
	          System.out.println("------");

	          // get <alumno>
	          NodeList list = doc.getElementsByTagName("alumno");

	          for (int temp = 0; temp < list.getLength(); temp++) 
	          {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) 
	              {

	                  Element element = (Element) node;

	                  // get alumno attribute
	                  String id = element.getAttribute("id_matricula");

	                  // get text
	                  String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
	                  String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
	                  String nota1 = element.getElementsByTagName("nota1").item(0).getTextContent();
	                  String nota2 = element.getElementsByTagName("nota2").item(0).getTextContent();
	                  String nota3 = element.getElementsByTagName("nota3").item(0).getTextContent();
	                  
	                  //PRACTICA
	                  NodeList practicaNodeList = element.getElementsByTagName("practica");
	                  
	                  
	                  String practica = practicaNodeList.item(0).getTextContent();
	                  
	                  //TIPO
	                  String tipo = practicaNodeList.item(0).getAttributes().getNamedItem("tipo").getTextContent();
	                  
	              
	                  //Caculo nota media y nota final
	            
	                  Double media=((Double.parseDouble(nota1)+ Double.parseDouble(nota2)+Double.parseDouble(nota3))/3);
	                  Double notatotal= media+ (Double.parseDouble(practica));
	                  
	                  //Sacar cada nota de practicas y el total
	                  
	                  Double totalpracticas=0.0;
	                  
	                  for(int i=0;i<practicaNodeList.getLength();i++)
	                  {
	                	practicaNodeList.item(i).getTextContent();
	                	totalpracticas+= Double.parseDouble(practicaNodeList.item(i).getTextContent());
	                  }
	                  
	                  //Nota total final
	                  Double nota_final=notatotal+totalpracticas;
	                  
	                  //IMPRIMIR DATOS
	                
	                  System.out.println("id_matricula alumno: " + id);
	                  System.out.println("Nombre: " + firstname);
	                  System.out.println("Apellidos: " + lastname);
	                  System.out.println("Nota 1: " + nota1);
	                  System.out.println("Nota 2: " + nota2);
	                  System.out.println("Nota 3: " + nota3);
	                  System.out.printf("Practica [html]: %,.2f [%s]%n%n", Float.parseFloat(practica), tipo);
	                  System.out.println("Nota media: " + media);
	                  System.out.println("Nota total de examenes: " + notatotal);
	                  System.out.println("Nota total de practicas: " + totalpracticas);
	                  System.out.println("Nota final: " + nota_final);
	                  
	                  
	                  
	                  
	                  
	              }
	          }
	      } catch (ParserConfigurationException | SAXException | IOException e) 
	      {
	          e.printStackTrace();
	      }
	  }
}
