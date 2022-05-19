package ar.edu.utn.link.correlativas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Alumno {
	
	private String nombre;
	private List<Curso> cursos;
	private Collection<Materia> materiasAprobadas;
	
	
	
	public Alumno(String nombre) {
		super();
		this.nombre = nombre;
		this.cursos = new ArrayList<>();
		this.materiasAprobadas = new ArrayList<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public Collection<Materia> getMateriasAprobadas() {
		return materiasAprobadas;
	}
	
	public void setMateriasAprobadas(Collection<Materia> materiasAprobadas) {
		this.materiasAprobadas = materiasAprobadas;
	}

	public void inscribir(Curso curso) throws NoCumpleCorrelativas {
		if(!curso.getMateria().cumpleCorrelativas(this.materiasAprobadas)) {
			throw new NoCumpleCorrelativas("No cumple con las correlativas");
		}else {
			curso.inscribir(this);
		}
		
	}
	
	

}
