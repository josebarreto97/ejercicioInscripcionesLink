package ar.edu.utn.link.correlativas;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class AlumnoTest {
	
	@Test
	public void testInscripcionOK() throws Exception {
		//Pre
		Alumno alumno = new Alumno("Ezequiel");
		Materia algoritmos = new Materia("algoritmos");
		alumno.setMateriasAprobadas(Arrays.asList(algoritmos));
		Materia paradigmas = new Materia("paradigmas");
		paradigmas.setCorrelativas(Arrays.asList(algoritmos));
		Curso curso = new Curso(paradigmas, 2022);
		
		//operatoria
		alumno.inscribir(curso);
		
		//postcond
		assertTrue(curso.estaInscripto(alumno));
	}
	
	@Test
	public void testInscripcionFaltaCorrelativa() throws Exception{
		//Pre
		Alumno alumno = new Alumno("Ezequiel");
		Materia discreta = new Materia("discreta");
		alumno.setMateriasAprobadas(Arrays.asList(discreta));
		Materia algoritmos = new Materia("algoritmos");
		Materia paradigmas = new Materia("paradigmas");
		paradigmas.setCorrelativas(Arrays.asList(algoritmos));
		Curso curso = new Curso(paradigmas,2022);
		
		//operatoria
		//alumno.inscribir(curso);
				
		//postcond
		assertThrows(NoCumpleCorrelativas.class, ()-> {alumno.inscribir(curso);});
	}
	
	@Test
	public void testAbrirCurso() throws NoCumpleConLaCantidadMinimaAlumnos{
		Alumno alumno = new Alumno("Ezequiel");
		Alumno alumno2 = new Alumno("Jose");
		Alumno alumno3 = new Alumno("Nicolas");

		Curso curso = new Curso(new Materia("SO"),2022);
		curso.setInscriptos(Stream.of(alumno,alumno2,alumno3).collect(Collectors.toSet()));
		curso.abrirCurso();
		
		assertTrue(curso.isAbierto());
	}
	
	@Test
	public void testabrirCursoException() throws NoCumpleConLaCantidadMinimaAlumnos {
		Alumno alumno = new Alumno("Ezequiel");
		Curso curso = new Curso(new Materia("SO"),2022);
		curso.setInscriptos(Stream.of(alumno).collect(Collectors.toSet()));
		
		assertThrows(NoCumpleConLaCantidadMinimaAlumnos.class, ()-> {curso.abrirCurso();});
	}

}
