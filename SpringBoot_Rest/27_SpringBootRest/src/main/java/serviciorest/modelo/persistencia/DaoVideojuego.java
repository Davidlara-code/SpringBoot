package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Videojuego;

/**
 * Patron DAO (Data Access Object), objeto que se encarga de hacer las consultas
 * a algun motor de persistencia (BBDD, Ficheros, etc). En este caso vamos a
 * simular que los datos estan guardados en una BBDD trabajando con una lista de
 * objetos cargada en memoria para simplificar el ejemplo.
 * 
 * Hay que tener en cuenta que para simplificar el ejemplo tambien se ha hecho
 * que el ID con el que se dan de alta las personas en la lista coincide
 * exactamente con la posicion del array que ocupan.
 * 
 * Mediante la anotacion @Component, damos de alta un unico objeto de esta clase
 * dentro del contexto de Spring, su ID sera el nombre de la case en notacion
 * lowerCamelCase
 * 
 */
@Component
public class DaoVideojuego {

	public List<Videojuego> listaJuegos;
	public int id;

	/**
	 * Cuando se cree el objeto dentro del contexto de Spring, se ejecutara su
	 * constructor, que creara las personas y las metera en una lista para que
	 * puedan ser consumidas por nuestros clientes
	 */
	public DaoVideojuego() {
		System.out.println("DaoVideojuegos -> Creando la lista de videojugos!");
		listaJuegos = new ArrayList<Videojuego>();
		Videojuego p1 = new Videojuego(id++, "Maincraft", "Mojang", 56);// ID: 0
		Videojuego p2 = new Videojuego(id++, "HARRY POTTER 9", "square", 19);// ID: 1
		Videojuego p3 = new Videojuego(id++, "residen evil II", "la zasca", 79);// ID: 2
		Videojuego p4 = new Videojuego(id++, "MAD MAX ", "EPIC GAMES", 85);// ID:3
		Videojuego p5 = new Videojuego(id++, "AZUR Y EL REY DE LA MONTAÑA", "Storm", 87);// ID:4
		listaJuegos.add(p1);
		listaJuegos.add(p2);
		listaJuegos.add(p3);
		listaJuegos.add(p4);
		listaJuegos.add(p5);
	}

	/**
	 * Devuelve una persona a partir de su posicion del array
	 * 
	 * @param posicion la posicion del arrya que buscamos
	 * @return la persona que ocupe en la posicion del array, null en caso de que no
	 *         exista o se haya ido fuera de rango del array
	 */
	public Videojuego get(int posicion) {
		try {
			return listaJuegos.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Videojuego fuera de rango");
			return null;
		}
	}

	/**
	 * Metodo que devuelve toda las personas del array
	 * 
	 * @return una lista con todas las personas del array
	 */
	public List<Videojuego> list() {
		return listaJuegos;
	}

	/**
	 * Metodo que introduce una persona al final de la lista
	 * 
	 * @param p la persona que queremos introducir
	 */
	public String add(Videojuego p) {

		for (Videojuego x : listaJuegos) {
			if (x.getNombre().equalsIgnoreCase(p.getNombre())) {
				return null;
			}

		}
		p.setId(id++);
		listaJuegos.add(p);
		return "añadido";
	}

	/**
	 * Borramos una persona de una posicion del array
	 * 
	 * @param posicion la posicion a borrar
	 * @return devolvemos la persona que hemos quitado del array, o null en caso de
	 *         que no exista.
	 */
	public Videojuego delete(int posicion) {
		try {
			return listaJuegos.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Videojuego fuera de rango");
			return null;
		}
	}

	/**
	 * Metodo que modifica una persona de una posicion del array
	 * 
	 * @param p contiene todos los datos que queremos modificar, pero p.getId()
	 *          contiene la posicion del array que queremos eliminar
	 * @return la persona modificada en caso de que exista, null en caso contrario
	 */
	public Videojuego update(Videojuego p) {
		try {
			Videojuego pAux = listaJuegos.get(p.getId());
			pAux.setNombre(p.getNombre());
			pAux.setCompañia(p.getCompañia());
			pAux.setNota(p.getNota());

			return pAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Videojuego fuera de rango");
			return null;
		}
	}

	/**
	 * Metodo que devuelve todas las personas por nombre. Como puede haber varias
	 * personas con el mismo nombre (HARRY) tengo que devolver una lista con todas
	 * las encontradas
	 * 
	 * @param nombre representa el nombre por el que vamos a hacer la busqueda
	 * @return una lista con las personas que coincidan en el nombre. La lista
	 *         estará vacia en caso de que no hay coincidencias
	 */
	public List<Videojuego> listByNombre(String nombre) {
		List<Videojuego> listajuegoAux = new ArrayList<Videojuego>();
		for (Videojuego p : listaJuegos) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {// contains()
				listajuegoAux.add(p);
			}
		}
		return listajuegoAux;
	}
}
