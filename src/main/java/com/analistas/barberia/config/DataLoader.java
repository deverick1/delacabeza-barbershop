package com.analistas.barberia.config;
import com.analistas.barberia.model.Cliente;
import com.analistas.barberia.model.Empleado;
import com.analistas.barberia.model.Servicio;
import com.analistas.barberia.repository.ClienteRepository;
import com.analistas.barberia.repository.EmpleadoRepository;
import com.analistas.barberia.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner{
    // 3. Inyección de Repositorios para guardar datos
    @Autowired
    private ClienteRepository clienteRepo;
    
    @Autowired
    private EmpleadoRepository empleadoRepo;
    
    @Autowired
    private ServicioRepository servicioRepo;

    @Override
    public void run(String... args) throws Exception {
        
        // 4. Verificamos si ya hay datos para evitar duplicados en reinicios
        if (clienteRepo.count() == 0) {
            System.out.println("Cargando datos iniciales...");
            
            // --- Cargar EMPLEADOS ---
            Empleado emp1 = new Empleado(null, "Alexis el Maestro");
            Empleado emp2 = new Empleado(null, "Erick el Barbero");
            empleadoRepo.saveAll(List.of(emp1, emp2));

            // --- Cargar CLIENTES ---
            Cliente cli1 = new Cliente(null, "Juan Perez", "3794123456");
            Cliente cli2 = new Cliente(null, "Maria Gomez", "3794987654");
            clienteRepo.saveAll(List.of(cli1, cli2));
            
            // --- Cargar SERVICIOS ---
            Servicio serv1 = new Servicio(null, 700.00, LocalDate.now(), "Corte de Pelo");
            Servicio serv2 = new Servicio(null, 500.00, LocalDate.now(), "Arreglo de Barba");
            Servicio serv3 = new Servicio(null, 1000.00, LocalDate.now(), "Combo Completo");
            servicioRepo.saveAll(List.of(serv1, serv2, serv3));
            
            System.out.println("Carga de datos inicial completada.");
        }
    }
}


