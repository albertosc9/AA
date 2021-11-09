package es.iestetuan.asc.fichtexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import es.iestetuan.asc.dao.IAlumno;
import es.iestetuan.asc.vo.Alumno;

public class AlumnoProperties implements IAlumno {

	 Properties prop = new Properties();
	
	
	
	@Override
	public Alumno getAlumno(long nia) throws IOException {

		Alumno alumno = new Alumno();
		FileInputStream input = new FileInputStream("recursos/info_config.properties");
		
		prop.load(input);
		
		FileReader fr = new FileReader(new File(prop.getProperty("origen")),Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		br.readLine();
		while((linea=br.readLine())!=null) {
			
			String [] partes = linea.split(",");
			
			if (nia==Long.parseLong(partes[0])) {
				
				if (partes.length>3) {
					alumno.setNia(nia);
					alumno.setNombre(partes[1]);
					alumno.setApellido1(partes[2]);
					alumno.setApellido2(partes[3]);
				}else {
					alumno.setNia(nia);
					alumno.setNombre(partes[1]);
					alumno.setApellido1(partes[2]);
				}
				break;
			}
			
			
		}
		return alumno;
	}

	@Override
	public List<Alumno> getAlumnos() {
	List<Alumno> lista = new ArrayList<Alumno>();
		
		
		
		try {
			InputStream input = new FileInputStream("recursos/info_config.properties");
			
			
			prop.load(input);
			
			File archivo = new File(prop.getProperty("origen"));
			FileReader fr = new FileReader(archivo,Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(fr);
			
			String datos;
			
			br.readLine();
			while((datos=br.readLine())!=null){
				String [] partes = datos.split(",");
				
				Alumno alumno = new Alumno();
					alumno.setNia(Long.parseLong(partes[0]));
					alumno.setNombre(partes[1]);
					alumno.setApellido1(partes[2]);
					alumno.setApellido2(partes[3]);
					
				lista.add(alumno);
					
					
			}
			
			
			
			br.close();
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		return lista;
		
	}

	@Override
	public void guardarUsuario(List<Alumno> lista) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			DOMImplementation domImp = db.getDOMImplementation();
			Document documento = domImp.createDocument(null, "xml", null);
			
			
			Element raiz = null; Element hijo=null; Element dato = null;
			Attr id;
			Text texto; 
			
			
			raiz = documento.createElement("alumnos");
			documento.getDocumentElement().appendChild(raiz);
			
			for (Alumno al : lista) {
				
				
				hijo = documento.createElement("alumno");
				raiz.appendChild(hijo);
				
				//crear atributo
				String ids = String.valueOf(al.getNia().toString());
				id = documento.createAttribute("nia");
				hijo.setAttributeNode(id);
				id.setTextContent(ids);
				
				//hijos de alumno
				
				dato = documento.createElement("nombre");
				hijo.appendChild(dato);
				texto = documento.createTextNode(al.getNombre());
				dato.appendChild(texto);
				
				dato = documento.createElement("apellido1");
				hijo.appendChild(dato);
				texto = documento.createTextNode(al.getApellido1());
				dato.appendChild(texto);
				
				dato = documento.createElement("apellido2");
				hijo.appendChild(dato);
				texto = documento.createTextNode(al.getApellido2());
				dato.appendChild(texto);
				
				
				
				
				
				
			}
			
			
			
			
			
			InputStream input = new FileInputStream("recursos/info_config.properties");
			
			prop.load(input);
			 
			
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				  Transformer transformer;
				transformer = transformerFactory.newTransformer();
				 DOMSource source = new DOMSource(documento);
				  StreamResult result = new StreamResult(new File(prop.getProperty("destino")));
				  transformer.transform(source, result);

			} catch (TransformerConfigurationException e) {
				
				e.printStackTrace();
			
			 
			  
				
			} catch (TransformerException e) {
				
				e.printStackTrace();
			}
			
			 catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
			
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
