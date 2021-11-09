package es.iestetuan.asc.dao;

import java.io.IOException;
import java.util.List;

import es.iestetuan.asc.vo.Alumno;



public interface IAlumno {

	public Alumno getAlumno(long nia) throws IOException;
	public List<Alumno> getAlumnos();
	public void guardarUsuario(List<Alumno>lista);
	public void altaAlumno(Alumno alumno);
	public void borrarAlumno(int nia);
	public void modificarAlumno(Alumno alumno);
}
