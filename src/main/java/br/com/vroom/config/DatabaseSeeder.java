package br.com.vroom.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vroom.model.CategoriaProblema;
import br.com.vroom.model.ModeloMoto;
import br.com.vroom.model.Moto;
import br.com.vroom.repository.MotoRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private MotoRepository motoRepository;


    @PostConstruct
    public void init() {
        List<Moto> motos = List.of(
            Moto.builder().chassi("1A2B3C4D5E6F7G8H9").modelo(ModeloMoto.MOTTUPOP).placa("QAS0R12").problema(CategoriaProblema.MECANICO).build(),
            Moto.builder().chassi("9X8Y7Z6W5V4U3T2S1").modelo(ModeloMoto.MOTTUSPORT).placa("POI1T34").problema(CategoriaProblema.ELETRICO).build(),
            Moto.builder().chassi("1Q2W3E4R5T6Y7U8I9").modelo(ModeloMoto.MOTTUE).placa("DFG0H56").problema(CategoriaProblema.DOCUMENTACAO).build(),
            Moto.builder().chassi("A1B2C3D4E5F6G7H8I").modelo(ModeloMoto.MOTTUPOP).placa("JKL2M78").problema(CategoriaProblema.ESTETICO).build(),
            Moto.builder().chassi("P0O9I8U7Y6T5R4E3W").modelo(ModeloMoto.MOTTUSPORT).placa("ZXV3N90").problema(CategoriaProblema.SEGURANCA).build(),
            Moto.builder().chassi("Z1X2C3V4B5N6M7A8S").modelo(ModeloMoto.MOTTUE).placa("QAW4S23").problema(CategoriaProblema.CONFORME).build(),
            Moto.builder().chassi("T1U2V3W4X5Y6Z7A8Q").modelo(ModeloMoto.MOTTUPOP).placa("RTY5V67").problema(CategoriaProblema.MECANICO).build(),
            Moto.builder().chassi("M1N2O3P4Q5R6S7T8A").modelo(ModeloMoto.MOTTUSPORT).placa("BVC6N89").problema(CategoriaProblema.ESTETICO).build(),
            Moto.builder().chassi("B1V2C3D4E5F6G7H8T").modelo(ModeloMoto.MOTTUE).placa("LMN7P01").problema(CategoriaProblema.SEGURANCA).build(),
            Moto.builder().chassi("W1X2Y3Z4A5B6C7D8E").modelo(ModeloMoto.MOTTUPOP).placa("OPQ8W23").problema(CategoriaProblema.DOCUMENTACAO).build()
        );

        motoRepository.saveAll(motos);
    }
}
