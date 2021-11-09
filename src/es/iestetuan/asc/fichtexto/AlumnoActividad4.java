package es.iestetuan.asc.fichtexto;

import java.io.File;
import java.io.IOException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iestetuan.asc.dao.IAlumno;
import es.iestetuan.asc.vo.Alumno;

public class AlumnoActividad4 implements IAlumno{

	@Override
	public Alumno getAlumno(long nia) throws IOException {
		
		return null;
	}

	@Override
	public List<Alumno> getAlumnos() {
		
		return null;
	}

	@Override
	public void guardarUsuario(List<Alumno> lista) {
		
		
	}
	private boolean buscarAlumno(Alumno alumno) {
		
		boolean encontrado = false;
		
	File xml = new File("recursos/alumnos-dam2-nuevos.xml");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(xml);
			
			NodeList nodos = documento.getElementsByTagName("alumno");		
			for (int i =0;i<nodos.getLength();i++) {
				Node nodo = nodos.item(i);
				Element dato = (Element)nodo;
				
				
				long id = Long.parseLong(dato.getAttribute("nia"));
				
				if (id==alumno.getNia()) {
					
					//dato.getElementsByTagName("nombre").item(0).setTextContent("juana");
					encontrado=true;
					break;
				}
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
		
		
		return encontrado;
	}
	public void altaAlumno(Alumno alumno) {
		if (buscarAlumno(alumno)) {
			
			boolean encontrado = false;
			File xml = new File("recursos/alumnos-dam2.xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(xml);
				
				NodeList nodos = doc.getElementsByTagName("alumno");
				
				for (int i=0;i<nodos.getLength();i++) {
					Node nodo = nodos.item(i);
					Element dato = (Element)nodo;
					
					long id = Long.parseLong(dato.getAttribute("nia"));
					
					if (id==alumno.getNia()) {
						encontrado =true;
						break;
						
					}
				}
				
				if (!encontrado) {
					
					
					Node raiz = doc.getFirstChild();
					
					Node elem = doc.getElementsByTagName("alumnos").item(0);
					
					
					Element usuario = doc.createElement("alumno");
					elem.appendChild(usuario);
					usuario.setAttribute("nia", alumno.getNia().toString());
					
					Element nombre = doc.createElement("nombre");
					nombre.appendChild(doc.createTextNode(alumno.getNombre()));
					usuario.appendChild(nombre);
					
					
					Element apellido1 = doc.createElement("apellido1");
					apellido1.appendChild(doc.createTextNode(alumno.getApellido1()));
					usuario.appendChild(apellido1);
					
					Element apellido2 = doc.createElement("apellido2");
					apellido2.appendChild(doc.createTextNode(alumno.getApellido2()));
					usuario.appendChild(apellido2);
					
					
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
				    Transformer transformer = null;
				
				        transformer = transformerFactory.newTransformer();
				    
				    DOMSource source = new DOMSource(doc);
				    StreamResult result = new StreamResult(new File("recursos/alumnos-dam2.xml"));
				    transformer.transform(source, result);
					
					
					
					
					
					
					
					
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
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
public void borrarAlumno(int nia) {
		
		File xml = new File("recursos/alumnos-dam2.xml");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			
			Document documento = db.parse(xml);
			
			
			NodeList nodos = documento.getElementsByTagName("alumno");
			
			for (int i=0;i<nodos.getLength();i++) {
				Node nodo = nodos.item(i);
				Element dato = (Element) nodo;
				
				int id = Integer.parseInt(dato.getAttribute("nia"));
				
				if (id==nia) {
					
					dato.getParentNode().removeChild(dato);
					
					
					
					
					
					
					
				}
			}
			
			
			
			
			
			
			
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = null;
		
		        transformer = transformerFactory.newTransformer();
		    
		    DOMSource source = new DOMSource(documento);
		    StreamResult result = new StreamResult(new File("recursos/alumnos-dam2.xml"));
		    transformer.transform(source, result);
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

public void modificarAlumno(Alumno alumno) {
	
	File xml = new File("recursos/alumnos-dam2.xml");
	
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	try {
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document documento = db.parse(xml);
		
		NodeList nodos = documento.getElementsByTagName("alumno");
		
		for (int i=0;i<nodos.getLength();i++) {
			
			Node nodo = nodos.item(i);
			Element elemento = (Element)nodo;
			
			long id = Long.parseLong(elemento.getAttribute("nia"));
			
			if (id==alumno.getNia()) {
				
				elemento.getElementsByTagName("nombre").item(0).setTextContent(alumno.getNombre());
				elemento.getElementsByTagName("apellido1").item(0).setTextContent(alumno.getApellido2());
				elemento.getElementsByTagName("apellido2").item(0).setTextContent(alumno.getApellido2());
			}
		}
		
		TransformerFactory trf = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		transformer = trf.newTransformer();
		
		DOMSource source = new DOMSource(documento);
		StreamResult resut = new StreamResult(new File("recursos/alumnos-dam2.xml"));
		transformer.transform(source, resut);
		
		
		
		/**
		 * TransformerFactory transformerFactory = TransformerFactory.newInstance();
	   
	    DOMSource source = new DOMSource(documento);
	    StreamResult result = new StreamResult(new File("recursos/alumnos-dam2.1.xml"));
	    transformer.transform(source, result);
		
	
		 */
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
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
}
