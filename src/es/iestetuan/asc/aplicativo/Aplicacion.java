package es.iestetuan.asc.aplicativo;

import java.io.IOException;
import java.util.List;

import es.iestetuan.asc.dao.IAlumno;
import es.iestetuan.asc.fichtexto.AlumnoActividad4;
import es.iestetuan.asc.fichtexto.AlumnoProperties;
import es.iestetuan.asc.fichtexto.AlumnoXML;
import es.iestetuan.asc.vo.Alumno;

public class Aplicacion {

	public static void main(String[] args) {
		
		
		IAlumno alumno = new AlumnoXML();
		
		try {
			System.out.println(alumno.getAlumno(171));
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("LISTA DE ALUMNOS----------");
		List<Alumno> Lista = alumno.getAlumnos();
		
		for (Alumno al : Lista) {
			System.out.println("nia "+al.getNia()+" "+al);
		}
		
		
		//creacion xml a partir de la lista 
		
		alumno.guardarUsuario(Lista);
		
		
		//ejercicio 3 
		IAlumno al2 = new AlumnoProperties();
		
		try {
			
			System.out.println(al2.getAlumno(116));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Alumno> lista1 = al2.getAlumnos();
		
		for (Alumno al : lista1){
			
			System.out.println(al.getNia()+" "+al);
		}
		
		//creacion xml actividad 2. 
		al2.guardarUsuario(lista1);
		
		
		//actividad 4. 
		
		IAlumno al3 = new AlumnoActividad4();
		
		//borrar Alumno
			al3.borrarAlumno(919);
		
		
	}

}
