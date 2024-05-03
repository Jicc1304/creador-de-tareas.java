package com.mycompany.organizadordetareas;

import java.util.InputMismatchException;
import java.util.Scanner;
// definicion de la clase Tarea
public class Tarea {
    private static int contadorTareas = 0;
    private String tareaId;
    private String descripcion;
    private String responsable;
    private boolean estado;
    private String fechaVencimiento;


    // Constructor vacío
    public Tarea() {
        this.tareaId = "";
        this.descripcion = "";
        this.responsable = "";
        this.estado = false;
        this.fechaVencimiento = "";
     
    }

    // Constructor con parámetros
    public Tarea(String descripcion, String responsable, boolean estado, String fechaVencimiento) {
        this.tareaId = generarId();
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
       
    }

    // Método para generar la numeración de las tareas
    private String generarId() {
        contadorTareas++;
        return "T" + String.format("%03d", contadorTareas);
    }
// Metodos Getters y Setters
    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
//Metodo que encarga de solicitar la informacion de cada tarea
    public void agregarTarea() {
        Scanner scanner = new Scanner(System.in);
 do {
   
    System.out.print("Ingrese la descripción de la tarea (mínimo 2 palabras): ");//Solicita la descripcion de la tarea
    descripcion = scanner.nextLine().trim(); // Eliminar espacios en blanco al principio y al final

    // Verificar si la descripción contiene al menos dos palabras y no son números o letras únicas
    if (!descripcion.matches(".*\\b\\w+\\b.*\\s+\\b\\w+\\b.*") || descripcion.matches("\\b\\w\\b")) {
        System.out.println("La descripción debe contener al menos dos palabras y no ser un solo número o letra.");
    }
} while (!descripcion.matches(".*\\b\\w+\\b.*\\s+\\b\\w+\\b.*") || descripcion.matches("\\b\\w\\b"));


        // Solicitar día
        int dia = 0;
        boolean diaValido = false;
        do {
            try {
                System.out.print("Ingrese el día (1-31): ");//Solicita día
                dia = scanner.nextInt();
                if (dia >= 1 && dia <= 31) {
                    diaValido = true;
                } else {
                    System.out.print("El día debe ser entre 1 y 31: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Ingrese un día válido: ");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        } while (!diaValido);

        // Solicitar mes
        int mes = 0;
        boolean mesValido = false;
        do {
            try {
                System.out.print("Ingrese el mes (1-12): ");//Solicita el mes
                mes = scanner.nextInt();
                if (mes >= 1 && mes <= 12) {
                    mesValido = true;
                } else {
                    System.out.print("El mes debe ser entre 1 y 12: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Ingrese un mes válido: ");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        } while (!mesValido);

        // Solicitar año
        int año = 0;
        boolean añoValido = false;
        do {
            try {
                System.out.print("Ingrese el año (mayor a 2000): ");//Solicita el año, mayor al 2000
                año = scanner.nextInt();
                if (año >= 2000) {
                    añoValido = true;
                } else {
                    System.out.print("El año debe ser mayor a 2000: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Ingrese un año válido: ");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        } while (!añoValido);

        // Construir la fecha de vencimiento en formato DD/MM/AAAA
        String fechaAvencer= String.format("%02d/%02d/%04d", dia, mes, año);

        // Asignar la fecha de vencimiento al atributo correspondiente
        this.fechaVencimiento = fechaAvencer;

        scanner.nextLine(); // Consumir el salto de línea restante

        // Solicitar nombre del responsable
        do {
            System.out.print("Ingrese el nombre del responsable (mínimo 3 caracteres): ");// Solicita el nombre del responsable
            responsable = scanner.nextLine();
        } while (responsable.length() < 3);

        tareaId = generarId();

        System.out.println("Tarea agregada correctamente.");// indica que la tarea se agrego correctamente
    }


 @Override
 // Reimprime la informacion tomada  en una sola cadena
public String toString() {
    String estadoStr = estado ? "Completada" : "Pendiente";
    String fechaVencimientoStr = fechaVencimiento.isEmpty() ? "N/A" : fechaVencimiento;
  

    return "Tarea ID: " + tareaId + "\nDescripción: " + descripcion + "\nResponsable: " + responsable +
            "\nEstado: " + estadoStr + "\nFecha de vencimiento: " + fechaVencimientoStr +  "\n";
}

}
