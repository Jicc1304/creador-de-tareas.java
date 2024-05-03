package com.mycompany.organizadordetareas;

import java.util.ArrayList;
import java.util.Scanner;

// Clase que gestiona las tareas, permitiendo agregar, ver, eliminar, marcar como completadas y filtrar tareas.
class GestorTareas {
    // Lista de tareas y objeto Scanner para entrada del usuario
    private ArrayList<Tarea> tareas;
    private Scanner scanner;

    // Constructor de la clase GestorTareas. Inicializa la lista de tareas y el Scanner.
    public GestorTareas() {
        tareas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Método para agregar una nueva tarea a la lista.
    public void agregarTarea() {
        // Crea una nueva tarea y la agrega a la lista de tareas
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.agregarTarea();
        tareas.add(nuevaTarea);
    }

    // Método para mostrar todas las tareas en la lista.
    public void verTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            System.out.println("----- Lista de Tareas -----");
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i));
            }
        }
    }

    // Método para eliminar una tarea de la lista.
    public void eliminarTarea() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas para eliminar.");
        } else {
            System.out.print("Ingrese el número de la tarea que desea eliminar: ");
            String idTareaEliminar = scanner.next().toUpperCase(); // Convertir a mayúsculas
            
            boolean tareaEncontrada = false;
            for (int i = 0; i < tareas.size(); i++) {
                Tarea tarea = tareas.get(i);
                if (tarea.getTareaId().toUpperCase().equals(idTareaEliminar)) { // Convertir a mayúsculas
                    tareas.remove(i);
                    tareaEncontrada = true;
                    System.out.println("La tarea ha sido eliminada correctamente.");
                    break;
                }
            }
            
            if (!tareaEncontrada) {
                System.out.println("Error: No se encontró ninguna tarea con el número especificado.");
            }
        }
    }

    // Método para marcar una tarea como completada.
    public void marcarComoCompletada() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas para marcar como completadas.");
        } else {
            System.out.print("Ingrese el número de la tarea que desea marcar como completada: ");
            String numeroTarea = scanner.next().toUpperCase();
            
            boolean tareaEncontrada = false;
            for (Tarea tarea : tareas) {
                if (tarea.getTareaId().equals(numeroTarea)) {
                    tarea.setEstado(true);
                    tareaEncontrada = true;
                    System.out.println("La tarea ha sido marcada como completada.");
                    break;
                }
            }
            
            if (!tareaEncontrada) {
                System.out.println("Error: No se encontró ninguna tarea con el número especificado.");
            }
        }
    }

    // Método para filtrar las tareas por estado o fecha de vencimiento.
    public void filtrarTareas() {
        System.out.println("----- Filtrar Tareas -----");
        System.out.println("1. Por Estado");
        System.out.println("2. Por Fecha de Vencimiento");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                filtrarPorEstado();
                break;
            case 2:
                filtrarPorFechaVencimiento();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Método para filtrar las tareas por estado (pendientes o completadas).
    private void filtrarPorEstado() {
        System.out.println("Filtrar por Estado:");
        System.out.println("1. Pendientes");
        System.out.println("2. Completadas");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        boolean estadoFiltrar = opcion == 1 ? false : true;
        System.out.println("----- Tareas " + (estadoFiltrar ? "Completadas" : "Pendientes") + " -----");
        for (Tarea tarea : tareas) {
            if (tarea.isEstado() == estadoFiltrar) {
                System.out.println(tarea);
            }
        }
    }

    // Método para filtrar las tareas por fecha de vencimiento.
    private void filtrarPorFechaVencimiento() {
        scanner.nextLine(); // Consumir nueva línea pendiente
        System.out.print("Ingrese la fecha de vencimiento (DD/MM/AAAA): ");
        String fecha = scanner.nextLine();
        System.out.println("----- Tareas con Fecha de Vencimiento " + fecha + " -----");
        for (Tarea tarea : tareas) {
            if (tarea.getFechaVencimiento().equals(fecha)) {
                System.out.println(tarea);
            }
        }
    }

    // Método para mostrar el menú de opciones y gestionar las acciones del usuario.
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("----- Menú de Opciones -----");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Ver Tareas");
            System.out.println("3. Eliminar Tarea");
            System.out.println("4. Marcar como Completada");
            System.out.println("5. Filtrar Tareas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    verTareas();
                    break;
                case 3:
                    eliminarTarea();
                    break;
                case 4:
                    marcarComoCompletada();
                    break;
                case 5:
                    filtrarTareas();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 6);
    }
}
