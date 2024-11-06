
import java.util.LinkedList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        System.out.println("Prestamos de equipos de la universidad San Juan de Dios");
        System.out.println();
        Scanner sc = new Scanner(System.in);

        Metodosedi objMetodosedi = new Metodosedi();
        Metodoseing objMetodoseing = new Metodoseing();
        Metodostab objMetodostab = new Metodostab();
        Metodoscomp objMetodoscomp = new Metodoscomp();

        LinkedList<Tableta> listatab = new LinkedList<>();
        LinkedList<computadores> listacomp = new LinkedList<>();
        LinkedList<prestamoseingenieria> listaeing = new LinkedList<>();
        LinkedList<prestamosediseño> listaedi = new LinkedList<>();
        LinkedList<computadores> listaPrestados = new LinkedList<>();
        LinkedList<Tableta> listaPrestadosdi = new LinkedList<>();

        listatab = objMetodostab.ImportarArchivo();
        listacomp = objMetodoscomp.ImportarArchivo();

        int opc = 0;
        do {
            System.out.println("Seleccione la opción que desea." +
                    "\n1. Estudiante diseño." +
                    "\n2. Estudiante ingeniería." +
                    "\n3. Ver inventario." +
                    "\n4. Salir");
            System.out.println();
            if (sc.hasNextInt()) {
                opc = sc.nextInt();
                switch (opc) {
                    case 1:
                        int opt = 0;
                        do {
                            System.out.println("Estudiante de diseño");
                            System.out.println("\n1. Solicitar préstamo de equipo." +
                                    "\n2. Modificar préstamo de equipo." +
                                    "\n3. Regresar el equipo." +
                                    "\n4. Buscar equipo." +
                                    "\n5. Volver al menú principal.");
                            System.out.println();
                            if (sc.hasNextInt()) {
                                opt = sc.nextInt();
                                switch (opt) {
                                    case 1:
                                        System.out.println("Solicitar préstamo de equipo.\n");
                                        listaedi = objMetodosedi.ImportarArchivo(listaedi);
                                        Metodosedi objMetodosedib = new Metodosedi();
                                        Tableta tb = new Tableta();
                                        String CedulaBuscard;

                                        System.out.println("Ingrese su cédula");
                                        CedulaBuscard = sc.next();
                                        prestamosediseño resultadi = objMetodosedib.Buscar(listaedi, CedulaBuscard);
                                        System.out.println();

                                        if (resultadi != null) {
                                            System.out.println("Ya hay un préstamo activo con esta cédula\n");
                                        } else {
                                            System.out.println("La cédula no tiene préstamo activo, "
                                             + "ingrésela de nuevo para continuar el proceso del préstamo\n");
                                            listaedi = objMetodosedi.LlenarLista(listaedi);

                                            tb.SeleccionarMarca();
                                            String MarcaBuscar = tb.getMarca();

                                            tb.Seleccionartamaño();
                                            Float TamanoBuscar = tb.getTamano();

                                            tb.SeleccionarPrecio();
                                            Float PrecioBuscar = tb.getPrecio();

                                            tb.SeleccionarAlmacenamiento();
                                            String AlmacenamientoBuscar = tb.getAlmacenamiento();

                                            tb.SeleccionarPeso();
                                            Float PesoBuscar = tb.getPeso();

                                            Tableta tabletaAsignada = null;
                                            String serialAsignado = null;

                                            for (Tableta tab : listatab) {
                                                if (tab.getMarca().equalsIgnoreCase(MarcaBuscar) &&
                                                        tab.getTamano() == TamanoBuscar &&
                                                        tab.getPrecio() == PrecioBuscar &&
                                                        tab.getAlmacenamiento().equalsIgnoreCase(AlmacenamientoBuscar)
                                                        &&
                                                        tab.getPeso() == PesoBuscar) {
                                                    tabletaAsignada = tab;
                                                    serialAsignado = tab.getSerial();
                                                    break;
                                                }
                                            }

                                            if (tabletaAsignada != null) {
                                                System.out.println("La tableta asignada es: ");
                                                System.out.println("Serial: " + tabletaAsignada.getSerial());
                                                System.out.println("Marca: " + tabletaAsignada.getMarca());
                                                System.out.println("Tamaño: " + tabletaAsignada.getTamano());
                                                System.out.println("Precio: " + tabletaAsignada.getPrecio());
                                                System.out.println(
                                                        "Almacenamiento: " + tabletaAsignada.getAlmacenamiento());
                                                System.out.println("Peso: " + tabletaAsignada.getPeso());
                                                System.out.println("------------------------------- \n");

                                                listatab.remove(tabletaAsignada);
                                                listaPrestadosdi.add(tabletaAsignada);

                                                for (prestamosediseño estudiante : listaedi) {
                                                    if (estudiante.getCedula().equals(CedulaBuscard)) {
                                                        estudiante.setSerial(serialAsignado);
                                                        System.out.println(
                                                                "El serial de la tableta asignada al estudiante es: "
                                                                        + serialAsignado);
                                                        System.out.println();
                                                        break;
                                                    }
                                                }

                                                objMetodosedi.ExportarArchivo(listaedi);

                                            } else {
                                                System.out.println(
                                                        "No se encontró una tableta que coincida con las características ingresadas.\n");
                                            }
                                        }
                                        break;

                                    case 2:
                                        listaedi = objMetodosedi.ImportarArchivo(listaedi);
                                        System.out.println("Ingrese la cédula del estudiante que desea modificar:");
                                        String cedulaBuscar = sc.next();
                                        System.out.println();

                                        prestamosediseño estudianteModificar = null;
                                        for (prestamosediseño est : listaedi) {
                                            if (est.getCedula().equals(cedulaBuscar)) {
                                                estudianteModificar = est;
                                                break;
                                            }
                                        }

                                        if (estudianteModificar == null) {
                                            System.out.println("No se encontró un estudiante con la cédula ingresada.\n");
                                            break;
                                        }

                                        int oppt = 0;
                                        do {
                                            System.out.println("Seleccione el registro que quiere modificar: ");
                                            System.out.println("1. Nombre.");
                                            System.out.println("2. Apellido.");
                                            System.out.println("3. Teléfono.");
                                            System.out.println("4. Modalidad de estudio.");
                                            System.out.println("5. Cantidad de asignaturas.");
                                            System.out.println("0. Salir."); 

                                            while (!sc.hasNextInt()) {
                                                System.out.println("Por favor, ingrese un número válido:");
                                                sc.next();
                                                System.out.println();
                                            }

                                            oppt = sc.nextInt();

                                            switch (oppt) {
                                                case 0:
                                                    System.out.println("Saliendo\n");
                                                    break;

                                                case 1:
                                                    System.out.println("Ingrese el nuevo nombre:");
                                                    String nuevoNombre = sc.next();
                                                    estudianteModificar.setNombre(nuevoNombre);
                                                    break;

                                                case 2:
                                                    System.out.println("Ingrese el nuevo apellido:");
                                                    String nuevoApellido = sc.next();
                                                    estudianteModificar.setApellido(nuevoApellido);
                                                    break;

                                                case 3:
                                                    System.out.println("Ingrese el nuevo teléfono:");
                                                    String nuevoTelefono = sc.next();
                                                    estudianteModificar.setTelefono(nuevoTelefono);
                                                    break;

                                                    case 4:
                                                    int op = 0;
                                                
                                                    do {
                                                        System.out.println("Seleccione la nueva modalidad de estudio:");
                                                        System.out.println("1. Presencial");
                                                        System.out.println("2. Virtual");
                                                        System.out.println("3. Asincrónica");

                                                        while (!sc.hasNextInt()) {
                                                            System.out.println("Por favor, ingrese un número válido:");
                                                            sc.next();
                                                        }
                                                
                                                        op = sc.nextInt();
                                                        System.out.println();
                                                
                                                        switch (op) {
                                                            case 1:
                                                                estudianteModificar.setModalidadEstudio("Presencial");
                                                                System.out.println("Modalidad de estudio actualizada a Presencial.\n");
                                                                break;
                                                
                                                            case 2:
                                                                estudianteModificar.setModalidadEstudio("Virtual");
                                                                System.out.println("Modalidad de estudio actualizada a Virtual.\n");
                                                                break;
                                                
                                                            case 3:
                                                                estudianteModificar.setModalidadEstudio("Asincrónica");
                                                                System.out.println("Modalidad de estudio actualizada a Asincrónica.\n");
                                                                break;
                                                
                                                            default:
                                                                System.out.println("Opción no válida. Intente de nuevo.");
                                                                break;
                                                        }
                                                
                                                    } while (op == 1 && op == 2);
                                                
                                                    objMetodosedi.ExportarArchivo(listaedi);
                                                    objMetodosedi.MostrarLista(listaedi);
                                                    break;
                                                

                                                case 5:
                                                    System.out.println("Ingrese la nueva cantidad de asignaturas:");
                                                    while (!sc.hasNextInt()) {
                                                        System.out.println("Por favor, ingrese una cantidad válida:");
                                                        sc.next();
                                                    }
                                                    int nuevaCantidad = sc.nextInt();
                                                    estudianteModificar.setCantidadAsignaturas(nuevaCantidad);
                                                    break;

                                                default:
                                                    System.out.println("Opción no válida. Intente de nuevo.");
                                                    break;
                                            }

                                        } while (oppt != 0);

                                        objMetodosedi.ExportarArchivo(listaedi);
                                        objMetodosedi.MostrarLista(listaedi);
                                        break;

                                    case 3:
                                        System.out.println("Regresar el equipo.\n");
                                        listaedi = objMetodosedi.ImportarArchivo(listaedi);

                                        System.out.println("Ingrese la cédula del estudiante:");
                                        String CedulaBuscar = sc.next();
                                        System.out.println();

                                        prestamosediseño prestamoEncontrado = objMetodosedi.Buscar(listaedi,
                                                CedulaBuscar);

                                        if (prestamoEncontrado != null) {
                                            System.out.println("Equipo asignado al estudiante:");
                                            System.out.println("Cédula: " + prestamoEncontrado.getCedula());
                                            System.out.println("Nombre: " + prestamoEncontrado.getNombre());
                                            System.out.println("Apellido: " + prestamoEncontrado.getApellido());
                                            System.out.println("-------------------------------\n");

                                            int Op = 0;
                                            do {
                                                System.out.println("Seleccione una opción:");
                                                System.out.println("1. Devolver el equipo.");
                                                System.out.println("2. Regresar al menú anterior.");
                                                System.out.println();

                                                if (sc.hasNextInt()) {
                                                    Op = sc.nextInt();

                                                    switch (Op) {
                                                        case 1:
                                                            System.out.println("Ingrese la cédula del estudiante:");
                                                            CedulaBuscard = sc.next();
                                                            System.out.println();
                                                            prestamosediseño estudiante = objMetodosedi
                                                                    .Buscar(listaedi, CedulaBuscard);

                                                            if (estudiante == null || estudiante.getSerial() == null) {
                                                                System.out.println(
                                                                        "No se encontró un préstamo activo con esta cédula.\n");
                                                            } else {
                                                                String serialRegresar = estudiante.getSerial();

                                                                Tableta equipoDevueltodTableta = null;
                                                                for (Tableta tableta : listaPrestadosdi) {
                                                                    if (tableta.getSerial().equals(serialRegresar)) {
                                                                        equipoDevueltodTableta = tableta;
                                                                        break;
                                                                    }
                                                                }

                                                                if (equipoDevueltodTableta != null) {
                                                                    listatab.add(equipoDevueltodTableta);
                                                                    listaPrestados.remove(equipoDevueltodTableta);
                                                                    listaedi.remove(estudiante);

                                                                    System.out.println(
                                                                            "El equipo ha sido regresado con éxito.\n");
                                                                } else {
                                                                    System.out.println(
                                                                            "Prestamo no encontrado para esta cedula.\n");
                                                                }

                                                                objMetodosedi.ExportarArchivo(listaedi);
                                                            }

                                                        case 2:
                                                            System.out.println("Regresando...\n");
                                                            break;

                                                        default:
                                                            System.out.println(
                                                                    "Opción no válida. Por favor, ingrese 1 o 2.\n");
                                                            break;
                                                    }
                                                } else {
                                                    System.out.println("Por favor, ingrese un número entero.\n");
                                                    sc.next();
                                                }
                                            } while (Op != 1 && Op != 2);
                                        } else {
                                            System.out.println(
                                                    "Cédula no encontrada en el sistema, realice una solicitud.\n");
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Buscar equipo");
                                        listatab = objMetodostab.ImportarArchivo();
                                        Metodostab objmetodostab = new Metodostab();
                                        String SerialBuscarb = "";
                                        System.out.println("Ingrese el serial");
                                        SerialBuscarb = sc.next();
                                        Tableta resultab = objmetodostab.Buscar(listatab, SerialBuscarb);

                                        if (resultab == null) {
                                            System.out.println("El registro no existe\n");
                                        } else {
                                            System.out.println("Serial: " + resultab.getSerial());
                                            System.out.println("Marca: " + resultab.getMarca());
                                            System.out.println("Tamaño: " + resultab.getTamano());
                                            System.out.println("Precio: " + resultab.getPrecio());
                                            System.out.println("Almacenamiento: " + resultab.getAlmacenamiento());
                                            System.out.println("Peso: " + resultab.getPeso());
                                            System.out.println("------------------------------- \n");
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Regresando al menú principal.\n");
                                        break;

                                    default:
                                        System.out.println("Opción del 1 al 5.\n");
                                        break;
                                }
                            } else {
                                System.out.println("Por favor, ingrese un número entero.\n");
                                sc.next();
                            }
                        } while (opt != 5);
                        break;

                    case 2:
                        int optt = 0;
                        do {
                            System.out.println("Estudiante de ingeniería");
                            System.out.println("\n1. Solicitar préstamo de equipo." +
                                    "\n2. Modificar préstamo de equipo." +
                                    "\n3. Regresar el equipo." +
                                    "\n4. Buscar equipo." +
                                    "\n5. Volver al menú principal.");
                            System.out.println();
                            if (sc.hasNextInt()) {
                                optt = sc.nextInt();
                                switch (optt) {

                                    case 1:
                                        System.out.println("Solicitar préstamo de equipo.\n");
                                        listaeing = objMetodoseing.ImportarArchivo();
                                        Metodoseing objMetodoseingb = new Metodoseing();
                                        computadores cp = new computadores();
                                        String CedulaBuscarb;

                                        System.out.println("Ingrese su cédula:");
                                        CedulaBuscarb = sc.next();
                                        prestamoseingenieria resulta = objMetodoseingb.Buscar(listaeing, CedulaBuscarb);
                                        System.out.println();

                                        if (resulta != null) {
                                            System.out.println("Ya hay un préstamo activo con esta cédula\n");
                                        } else {
                                            System.out.println(
                                                    "La cédula no tiene préstamo activo, ingrésela de nuevo para continuar el proceso del préstamo\n");
                                            listaeing = objMetodoseing.LLenarLista(listaeing);

                                            cp.SeleccionarMarca();
                                            String MarcaBuscar = cp.getMarca();
                                            cp.Seleccionartamaño();
                                            Float TamanoBuscar = cp.getTamano();
                                            cp.SeleccionarPrecio();
                                            Float PrecioBuscar = cp.getPrecio();
                                            cp.SeleccionarSistemaOperativo();
                                            String SOBuscar = cp.getSistemaOperativo();
                                            cp.SeleccionarPr();
                                            String ProcesadorBuscar = cp.getProcesador();

                                            computadores computadorAsignado = null;
                                            String serialAsignado = null;
                                            for (computadores comp : listacomp) {
                                                if (comp.getMarca().equalsIgnoreCase(MarcaBuscar) &&
                                                        comp.getTamano() == TamanoBuscar &&
                                                        comp.getPrecio() == PrecioBuscar &&
                                                        comp.getSistemaOperativo().equalsIgnoreCase(SOBuscar) &&
                                                        comp.getProcesador().equalsIgnoreCase(ProcesadorBuscar)) {
                                                    computadorAsignado = comp;
                                                    serialAsignado = comp.getSerial();
                                                    break;
                                                }
                                            }

                                            if (computadorAsignado != null) {
                                                System.out.println("El computador asignado es:");
                                                System.out.println("Serial: " + computadorAsignado.getSerial());
                                                System.out.println("Marca: " + computadorAsignado.getMarca());
                                                System.out.println("Tamaño: " + computadorAsignado.getTamano());
                                                System.out.println("Precio: " + computadorAsignado.getPrecio());
                                                System.out.println("Sistema operativo: " + computadorAsignado.getSistemaOperativo());
                                                System.out.println("Procesador: " + computadorAsignado.getProcesador());
                                                System.out.println("------------------------------- \n");

                                                listacomp.remove(computadorAsignado);
                                                listaPrestados.add(computadorAsignado); 

                                                for (prestamoseingenieria estudiante : listaeing) {
                                                    if (estudiante.getCedula().equals(CedulaBuscarb)) {
                                                        estudiante.setSerial(serialAsignado);
                                                        System.out.println("El serial del computador asignado al estudiante es: "
                                                                        + serialAsignado);
                                                        System.out.println();
                                                        break;
                                                    }
                                                }

                                                listaeing = objMetodoseing.ExportarArchivo(listaeing);

                                            } else {
                                                System.out.println(
                                                        "No se encontró un computador que coincida con las características ingresadas.\n");
                                            }
                                        }
                                        break;

                                    case 2:
                                        listaeing = objMetodoseing.ImportarArchivo();

                                        System.out.println("Ingrese la cédula del estudiante que desea modificar:");
                                        String cedulaBuscarm = sc.next();

                                        prestamoseingenieria estudianteModificar = null;
                                        for (prestamoseingenieria est : listaeing) {
                                            if (est.getCedula().equals(cedulaBuscarm)) {
                                                estudianteModificar = est;
                                                break;
                                            }
                                        }

                                        if (estudianteModificar == null) {
                                            System.out.println("No se encontró un estudiante con la cédula ingresada.");
                                            break;
                                        }

                                        int oppt = 0;

                                        do {
                                            System.out.println("Seleccione el registro que quiere modificar: ");
                                            System.out.println("1. Nombre.");
                                            System.out.println("2. Apellido.");
                                            System.out.println("3. Teléfono.");
                                            System.out.println("4. Promedio.");
                                            System.out.println("5. Número de semestre.");
                                            System.out.println("0. Salir.");

                                            while (!sc.hasNextInt()) {
                                                System.out.println("Por favor, ingrese un número válido:");
                                                sc.next();
                                            }

                                            oppt = sc.nextInt();
                                            System.out.println();

                                            switch (oppt) {
                                                case 0:
                                                    System.out.println("Saliendo\n");
                                                    break;

                                                case 1:
                                                    System.out.println("Ingrese el nuevo nombre:");
                                                    String nuevoNombre = sc.next();
                                                    estudianteModificar.setNombre(nuevoNombre);
                                                    System.out.println("DATO MODIFICADO\n");
                                                    break;

                                                case 2:
                                                    System.out.println("Ingrese el nuevo apellido:");
                                                    String nuevoApellido = sc.next();
                                                    estudianteModificar.setApellido(nuevoApellido);
                                                    System.out.println("DATO MODIFICADO\n");
                                                    break;

                                                case 3:
                                                    System.out.println("Ingrese el nuevo teléfono:");
                                                    String nuevoTelefono = sc.next();
                                                    estudianteModificar.setTelefono(nuevoTelefono);
                                                    System.out.println("DATO MODIFICADO\n");
                                                    break;

                                                case 4:
                                                    System.out.println("Ingrese el nuevo promedio:");
                                                    while (!sc.hasNextFloat()) {
                                                        System.out.println("Por favor, ingrese un promedio válido:");
                                                        sc.next();
                                                    }
                                                    float nuevoPromedio = sc.nextFloat();
                                                    estudianteModificar.setPromedio(nuevoPromedio);
                                                    System.out.println("DATO MODIFICADO\n");
                                                    break;

                                                case 5:
                                                    System.out.println("Ingrese el nuevo número de semestre:");
                                                    while (!sc.hasNextInt()) {
                                                        System.out.println(
                                                                "Por favor, ingrese un número de semestre válido:");
                                                        sc.next();
                                                    }
                                                    int nuevoSemestre = sc.nextInt();
                                                    estudianteModificar.setNumSemestre(nuevoSemestre);
                                                    System.out.println("DATO MODIFICADO\n");
                                                    break;

                                                default:
                                                    System.out.println("Opción no válida. Intente de nuevo.");
                                                    break;
                                            }

                                        } while (oppt != 0);
                                        objMetodoseing.ExportarArchivo(listaeing);
                                        objMetodoseing.MostrarLista(listaeing);
                                        break;

                                    case 3:
                                        System.out.println("Regresar el equipo.\n");
                                        listaeing = objMetodoseing.ImportarArchivo();

                                        System.out.println("Ingrese la cédula del estudiante:");
                                        String cedulaBuscar = sc.next();
                                        System.out.println();

                                        prestamoseingenieria prestamoEncontrado = objMetodoseing.Buscar(listaeing,
                                                cedulaBuscar);

                                        if (prestamoEncontrado != null) {
                                            System.out.println("Equipo asignado al estudiante:");
                                            System.out.println("Cédula: " + prestamoEncontrado.getCedula());
                                            System.out.println("Nombre: " + prestamoEncontrado.getNombre());
                                            System.out.println("Apellido: " + prestamoEncontrado.getApellido());
                                            System.out.println("-------------------------------\n");

                                            int Op = 0;
                                            do {
                                                System.out.println("Seleccione una opción:");
                                                System.out.println("1. Devolver el equipo.");
                                                System.out.println("2. Regresar al menú anterior.");
                                                System.out.println();

                                                if (sc.hasNextInt()) {
                                                    Op = sc.nextInt();

                                                    switch (Op) {
                                                        case 1:
                                                            System.out.println("Ingrese la cédula del estudiante:");
                                                            CedulaBuscarb = sc.next();
                                                            System.out.println();
                                                            prestamoseingenieria estudiante = objMetodoseing
                                                                    .Buscar(listaeing, CedulaBuscarb);

                                                            if (estudiante == null || estudiante.getSerial() == null) {
                                                                System.out.println(
                                                                        "No se encontró un préstamo activo con esta cédula.\n");
                                                            } else {
                                                                String serialRegresar = estudiante.getSerial();
                                                                computadores equipoDevuelto = null;
                                                                for (computadores comp : listaPrestados) {
                                                                    if (comp.getSerial().equals(serialRegresar)) {
                                                                        equipoDevuelto = comp;
                                                                        break;
                                                                    }
                                                                }

                                                                if (equipoDevuelto != null) {
                                                                    listacomp.add(equipoDevuelto);
                                                                    listaPrestados.remove(equipoDevuelto);
                                                                    listaeing.remove(estudiante);

                                                                    System.out.println(
                                                                            "El equipo ha sido regresado con éxito.\n");
                                                                } else {
                                                                    System.out.println(
                                                                            "No se encontró un equipo con el serial proporcionado en la lista de equipos prestados.\n");
                                                                }

                                                                listaeing = objMetodoseing.ExportarArchivo(listaeing);
                                                            }

                                                        case 2:
                                                            System.out.println("Regresando...\n");
                                                            break;

                                                        default:
                                                            System.out.println(
                                                                    "Opción no válida. Por favor, ingrese 1 o 2.\n");
                                                            break;
                                                    }
                                                } else {
                                                    System.out.println("Por favor, ingrese un número entero.\n");
                                                    sc.next();
                                                }
                                            } while (Op != 1 && Op != 2);
                                        } else {
                                            System.out.println(
                                                    "Cédula no encontrada en el sistema, realice una solicitud.\n");
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Buscar equipo\n");
                                        listacomp = objMetodoscomp.ImportarArchivo();
                                        Metodoscomp objMetodoscompb = new Metodoscomp();
                                        String SerialBuscarb = "";
                                        System.out.println("Ingrese el serial que desea buscar");
                                        SerialBuscarb = sc.next();
                                        computadores result = objMetodoscompb.Buscar(listacomp, SerialBuscarb);

                                        if (result != null) {

                                            System.out.println();
                                            System.out.println("Serial: " + " " + result.getSerial());
                                            System.out.println("Marca: " + " " + result.getMarca());
                                            System.out.println("Tamaño: " + " " + result.getTamano());
                                            System.out.println("Precio: " + " " + result.getPrecio());
                                            System.out.println("Sistema Operativo: " + " " + result.getSistemaOperativo());
                                            System.out.println("Procesador: " + " " + result.getProcesador());
                                            System.out.println();
                                            System.out.println("------------------------------- \n");
                                        } else {
                                            System.out.println("El registro no existe\n");
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Regresando al menú principal.\n");
                                        break;

                                    default:
                                        System.out.println("Opción no válida, seleccione del 1 al 5.\n");
                                        break;
                                }
                            } else {
                                System.out.println("Por favor, ingrese un número entero.\n");
                                sc.next();
                            }
                        } while (optt != 5);
                        break;

                    case 3:
                        int op = 0;
                        do {
                            System.out.println("Ver inventario");
                            System.out.println("1.Estudiante de diseño, 2. Estudiante de ingenieria, 3.Volver\n");
                            if (sc.hasNextInt()) {
                                op = sc.nextInt();
                                switch (op) {
                                    case 1:
                                        System.out.println();
                                        System.out.println("          TABLETS\n");
                                        System.out.println();
                                        objMetodostab.MostrarLista(listatab);
                                        System.out.println();
                                        break;
                                    case 2:
                                        System.out.println();
                                        System.out.println("          COMPUTADORES\n");
                                        System.out.println();
                                        objMetodoscomp.MostrarLista(listacomp);
                                        System.out.println();
                                        break;
                                    case 3:
                                        System.out.println("Volviendo...\n");
                                        break;
                                    default:
                                        System.out.println("Opción no válida. Por favor, elija una opción de 1 a 3.\n");
                                        break;
                                }
                            } else {
                                System.out.println("Por favor, ingrese un número entero.\n");
                                sc.next();
                            }
                        } while (op != 3);
                        break;

                    case 4:
                        System.out.println("Saliste");
                        sc.close();
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción de 1 a 4.\n");
                        break;
                }
            } else {
                System.out.println("Por favor, ingrese un número entero.\n");
                sc.next();
            }
        } while (opc != 4);
    }
}