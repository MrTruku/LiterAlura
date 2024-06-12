package com.alurachallengue.LiterAlura.principal;

import com.alurachallengue.LiterAlura.model.Datos;
import com.alurachallengue.LiterAlura.model.DatosLibros;
import com.alurachallengue.LiterAlura.service.ConsumoAPI;
import com.alurachallengue.LiterAlura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    //Atributos de uso (scope) global podemos usarlo en cualquier parte de nuestro codigo.

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<Datos> datosLibros =new ArrayList<>();


    //metodo que me permite mostrar el menu en la consola

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 7) {
            //declaro la variable Menu Principal

            var menuPrincipal = """
            *******************************
            Escoja alguna de la siguiente opciones enumerada:
           
            1 - Buscar libros por tíulo.
            2 - Buscar libros por autor.
            3 - Lista de libros guardados.
            4-  Lista de autores registrados.
            5 - Lista de autores vivos en determinado año.
            6 - Lista de libros por idioma.
            7 - Salir.
            """;
            System.out.println(menuPrincipal);
            opcion = teclado.nextInt();
            teclado.nextLine();

            //en funcion de la opcion elejida se ejecuta el swithc
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibroPorAutor();
                    break;
                case 3:
                    listarLibrosBuscados();
                    break;
                case 4:
                    ListaDeAutoresRegistrados();
                    break;
                case 5:
                    listarAutoresVivosPorFecha();
                    break;
                case 6:
                    listarLibrosPorIdioma();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }


    //Metodos utilizados


    private Datos buscarLibro() {
        System.out.println("Por favor ingrese el nombre del libro que desee buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado: ");
            System.out.println(libroBuscado.get());

        } else {
            System.out.println("Libro no encontrado");
        }

        return datosBusqueda;
    }
    private void buscarLibroPorAutor() {
    }

    private void listarLibrosBuscados() {
        Datos datos = buscarLibro();
        datosLibros.add(datos);
        System.out.println(datos);
    }
    private void listarLibrosPorIdioma() {
    }

    private void listarAutoresVivosPorFecha() {

    }

    private void ListaDeAutoresRegistrados() {
    }

}