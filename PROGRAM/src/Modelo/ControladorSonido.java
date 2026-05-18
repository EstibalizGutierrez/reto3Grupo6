package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ControladorSonido {
	ArrayList<Cancion> canciones;
	int cancionEnReproduccion;
	Clip cancionEnCurso;
	
	public ControladorSonido(ArrayList<Cancion> canciones, int cancion) {
		this.canciones = canciones;
		cancionEnReproduccion = cancion;
		try {
			cancionEnCurso = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setCancionEnReproduccion(int can) {
		if (canciones.get(cancionEnReproduccion).sonando()) {
			cancionEnCurso.stop();
		}

		if (!(can < 0 || can >= canciones.size())) {
			this.cancionEnReproduccion = can;
			reproducir();
		}
	}
	
	public void reproducir() {
		try {
			if (canciones.get(cancionEnReproduccion).sonando()) {
				cancionEnCurso.stop();
				cancionEnCurso.close();
			}
			cancionEnCurso.open(AudioSystem.getAudioInputStream(new File(canciones.get(cancionEnReproduccion).getCancion())));
			cancionEnCurso.start();

		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public void pausar() {
		cancionEnCurso.stop(); // Detiene la reproducción
		cancionEnCurso.close(); 
	}
	
	public int getCancionActualIndice() {
	    return this.cancionEnReproduccion;
	}
}
