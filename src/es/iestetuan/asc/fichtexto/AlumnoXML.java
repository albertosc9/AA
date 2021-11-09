package es.iestetuan.asc.fichtexto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iestetuan.asc.dao.IAlumno;
import es.iestetuan.asc.vo.Alumno;

public class AlumnoXML implements IAlumno{

	@Override
	public Alumno getAlumno(long nia) throws IOException {
		
		Alumno alumno = new Alumno();
		
		File xml = new File("recursos/alumnos-dam2-nuevos.xml");
		
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			
			
			NodeList nodos = doc.getElementsByTagName("alumno");
			
			for (int i=0;i<nodos.getLength();i++) {
				Node nodo = nodos.item(i);
				Element dato = (Element)nodo;
				
				long id = Long.parseLong(dato.getAttribute("nia"));
				if (id==nia) {
					alumno.setNia(nia);
					alumno.setNie(dato.getElementsByTagName("nie").item(0).getTextContent());
					alumno.setNombre(dato.getElementsByTagName("nombre").item(0).getTextContent());
					alumno.setApellido1(dato.getElementsByTagName("apellido1").item(0).getTextContent());
					alumno.setApellido2(dato.getElementsByTagName("apellido2").item(0).getTextContent());
					alumno.setEmail(dato.getElementsByTagName("email").item(0).getTextContent());
					break;
				}
			}
			
			
			
		
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alumno;
	}

	@Override
	public List<Alumno> getAlumnos() {
		
		List<Alumno> Lista = new ArrayList<Alumno>();
		
		File xml = new File("recursos/alumnos-dam2-nuevos.xml");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document documento = db.parse(xml);
			
			NodeList nodos = documento.getElementsByTagName("alumno");
			
			for (int i=0;i<nodos.getLength();i++) {
				
				Node alumno = nodos.item(i);
				Element elemento = (Element)alumno;
				
				Alumno al = new Alumno();
				
				Long id = Long.parseLong(elemento.getAttribute("nia"));
				al.setNia(id);
				al.setNombre(elemento.getElementsByTagName("nombre").item(0).getTextContent());
				al.setApellido1(elemento.getElementsByTagName("apellido1").item(0).getTextContent());
				al.setApellido2(elemento.getElementsByTagName("apellido2").item(0).getTextContent());
				al.setEmail(elemento.getElementsByTagName("email").item(0).getTextContent());
				
				Lista.add(al);
				
				
				
			}
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Lista;
	}

	@Override
	public void guardarUsuario(List<Alumno> lista) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			
			
			
			Document doc = db.newDocument();
			
			DOMImplementation dom = db.getDOMImplementation();
			doc = dom.createDocument(null, "xml", null);
			
			
			Element alumnos = doc.createElement("alunnos");
			doc.getDocumentElement().appendChild(alumnos);
			
			for (Alumno al : lista) {
				
				
				Element alumno = doc.createElement("alumno");
				alumnos.appendChild(alumno);
				alumno.setAttribute("nia",al.getNia().toString());
				
				
				Element nie = doc.createElement("nie");
				nie.appendChild(doc.createTextNode(al.getNie()));
				alumno.appendChild(nie);
				
				Element nombre = doc.createElement("nombre");
				nombre.appendChild(doc.createTextNode(al.getNombre()));
				alumno.appendChild(nombre);
				
				Element apellido1 = doc.createElement("apellido1");
				apellido1.appendChild(doc.createTextNode(al.getApellido1()));
				alumno.appendChild(apellido1);
				
				Element apellido2 = doc.createElement("apellido2");
				apellido2.appendChild(doc.createTextNode(al.getApellido2()));
				alumno.appendChild(apellido2);
				
				Element email = doc.createElement("email");
				email.appendChild(doc.createTextNode(al.getEmail()));
				alumno.appendChild(email);
				
				
				
				
				
				
				
			}
			
			
	
			TransformerFactory tf = TransformerFactory.newInstance();
			
			Transformer transformer = tf.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File("recursos/alumnos-dam2-nuevos-v1.xml"));
			
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
			
			
			
			
			
			
			
		
		
		
		
		
		
	}

	@Override
	public void altaAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarAlumno(int nia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		
	}



}
