import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);

    // Colecciones solicitadas en el parcial
    static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    static HashSet<String> placas = new HashSet<>();
    static HashMap<String, Double> totalesPorTipo = new HashMap<>();

    public static void main(String[] args) {

        int opcion;

        do {

            mostrarMenu();

            try {

                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {

                    case 1:
                        registrarVehiculo();
                        break;

                    case 2:
                        mostrarVehiculos();
                        break;

                    case 3:
                        buscarVehiculo();
                        break;

                    case 4:
                        mostrarMayorCosto();
                        break;

                    case 5:
                        mostrarTotalGeneral();
                        break;

                    case 6:
                        mostrarTotalesPorTipo();
                        break;

                    case 7:
                        System.out.println("\nPrograma finalizado.");
                        break;

                    default:
                        System.out.println("\nOpción inválida.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "\nError: debe ingresar un número."
                );

                opcion = 0;

            } finally {

                System.out.println(
                        "\n--- Fin de la operación ---\n"
                );
            }

        } while (opcion != 7);

        entrada.close();
    }


    public static void mostrarMenu() {

        System.out.println("======================================");
        System.out.println("       ESTACIONAMIENTO JAVA");
        System.out.println("======================================");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Mostrar todos los vehículos");
        System.out.println("3. Buscar vehículo por placa");
        System.out.println("4. Mostrar vehículo con mayor costo");
        System.out.println("5. Mostrar total general recaudado");
        System.out.println("6. Mostrar total recaudado por tipo");
        System.out.println("7. Salir");
        System.out.println("======================================");
    }

    public static void registrarVehiculo() {

        System.out.println("\n===== REGISTRAR VEHÍCULO =====");

        String placa;

        do {

            System.out.print("Placa: ");
            placa = entrada.nextLine().trim().toUpperCase();

            if (placa.isEmpty()) {

                System.out.println(
                        "La placa no puede estar vacía."
                );
            }

        } while (placa.isEmpty());

        if (placas.contains(placa)) {

            System.out.println(
                    "Error: esa placa ya está registrada."
            );

            return;
        }

        // Validar propietario
        String propietario;

        do {

            System.out.print("Propietario: ");
            propietario = entrada.nextLine().trim();

            if (propietario.isEmpty()) {

                System.out.println(
                        "El propietario no puede estar vacío."
                );
            }

        } while (propietario.isEmpty());

        // Validar hora de ingreso
        String horaIngreso;

        do {

            System.out.print("Hora de ingreso: ");
            horaIngreso = entrada.nextLine().trim();

            if (horaIngreso.isEmpty()) {

                System.out.println(
                        "La hora de ingreso no puede estar vacía."
                );
            }

        } while (horaIngreso.isEmpty());

        // Validar horas utilizadas
        int horasUtilizadas;

        while (true) {

            try {

                System.out.print("Horas utilizadas: ");

                horasUtilizadas =
                        Integer.parseInt(entrada.nextLine());

                if (horasUtilizadas <= 0) {

                    System.out.println(
                            "Las horas deben ser mayores que cero."
                    );

                } else {

                    break;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: debe ingresar un número entero."
                );
            }
        }


        int tipo;

        while (true) {

            try {

                System.out.println("\nTipo de vehículo:");
                System.out.println("1. Automóvil");
                System.out.println("2. Motocicleta");
                System.out.print("Seleccione: ");

                tipo =
                        Integer.parseInt(entrada.nextLine());

                if (tipo == 1 || tipo == 2) {

                    break;
                }

                System.out.println(
                        "Tipo de vehículo inválido."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: debe ingresar 1 o 2."
                );
            }
        }

        // POLIMORFISMO
        Vehiculo vehiculo;

        if (tipo == 1) {

            vehiculo = new Automovil(
                    placa,
                    propietario,
                    horaIngreso,
                    horasUtilizadas
            );

        } else {

            vehiculo = new Motocicleta(
                    placa,
                    propietario,
                    horaIngreso,
                    horasUtilizadas
            );
        }


        double costo = vehiculo.calcularCosto();

        String nombreTipo;

        if (tipo == 1) {

            nombreTipo = "Automóvil";

        } else {

            nombreTipo = "Motocicleta";
        }

        // Guardar en ArrayList
        vehiculos.add(vehiculo);

        // Guardar placa en HashSet
        placas.add(placa);

        // Actualizar HashMap
        totalesPorTipo.put(
                nombreTipo,
                totalesPorTipo.getOrDefault(
                        nombreTipo,
                        0.0
                ) + costo
        );

        System.out.println(
                "\nVehículo registrado correctamente."
        );

        System.out.println(
                "Tipo: " + nombreTipo
        );

        System.out.println(
                "Costo calculado: Q" +
                        String.format("%.2f", costo)
        );
    }


    public static void mostrarVehiculos() {

        System.out.println(
                "\n===== VEHÍCULOS REGISTRADOS ====="
        );

        if (vehiculos.isEmpty()) {

            System.out.println(
                    "No hay vehículos registrados."
            );

            return;
        }

        for (Vehiculo vehiculo : vehiculos) {

            System.out.println(
                    "------------------------------"
            );

            vehiculo.mostrarInformacion();

            System.out.println(
                    "Tipo: " + obtenerTipo(vehiculo)
            );
        }

        System.out.println(
                "------------------------------"
        );
    }


    public static void buscarVehiculo() {

        System.out.println(
                "\n===== BUSCAR VEHÍCULO ====="
        );

        System.out.print("Ingrese la placa: ");

        String placaBuscada =
                entrada.nextLine().trim().toUpperCase();

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getPlaca().equals(placaBuscada)) {

                System.out.println(
                        "\nVehículo encontrado:"
                );

                System.out.println(
                        "------------------------------"
                );

                vehiculo.mostrarInformacion();

                System.out.println(
                        "Tipo: " + obtenerTipo(vehiculo)
                );

                return;
            }
        }

        System.out.println(
                "No se encontró un vehículo con esa placa."
        );
    }


    public static void mostrarMayorCosto() {

        System.out.println(
                "\n===== MAYOR COSTO ====="
        );

        if (vehiculos.isEmpty()) {

            System.out.println(
                    "No hay vehículos registrados."
            );

            return;
        }

        Vehiculo mayor = vehiculos.get(0);

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.calcularCosto() >
                    mayor.calcularCosto()) {

                mayor = vehiculo;
            }
        }

        System.out.println(
                "Vehículo con mayor costo:"
        );

        System.out.println(
                "------------------------------"
        );

        mayor.mostrarInformacion();

        System.out.println(
                "Tipo: " + obtenerTipo(mayor)
        );
    }



    public static void mostrarTotalGeneral() {

        System.out.println(
                "\n===== TOTAL GENERAL ====="
        );

        if (vehiculos.isEmpty()) {

            System.out.println(
                    "No hay vehículos registrados."
            );

            return;
        }

        double total = 0;

        for (Vehiculo vehiculo : vehiculos) {

            total += vehiculo.calcularCosto();
        }

        System.out.println(
                "Total recaudado: Q" +
                        String.format("%.2f", total)
        );
    }


    public static void mostrarTotalesPorTipo() {

        System.out.println(
                "\n===== TOTAL POR TIPO ====="
        );

        if (totalesPorTipo.isEmpty()) {

            System.out.println(
                    "No hay datos registrados."
            );

            return;
        }

        // Recalcular dinámicamente el HashMap
        totalesPorTipo.clear();

        for (Vehiculo vehiculo : vehiculos) {

            String tipo = obtenerTipo(vehiculo);

            double costo = vehiculo.calcularCosto();

            totalesPorTipo.put(
                    tipo,
                    totalesPorTipo.getOrDefault(
                            tipo,
                            0.0
                    ) + costo
            );
        }

        for (String tipo : totalesPorTipo.keySet()) {

            System.out.println(
                    tipo + ": Q" +
                            String.format(
                                    "%.2f",
                                    totalesPorTipo.get(tipo)
                            )
            );
        }
    }

    // ==============================
    // OBTENER TIPO
    // ==============================

    public static String obtenerTipo(Vehiculo vehiculo) {

        if (vehiculo instanceof Automovil) {

            return "Automóvil";

        } else if (vehiculo instanceof Motocicleta) {

            return "Motocicleta";
        }

        return "Desconocido";
    }
}