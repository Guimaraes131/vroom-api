package br.com.vroom.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vroom.model.CategoriaProblema;
import br.com.vroom.model.ModeloMoto;
import br.com.vroom.model.Moto;
import br.com.vroom.model.Setor;
import br.com.vroom.model.Tag;
import br.com.vroom.repository.MotoRepository;
import br.com.vroom.repository.SetorRepository;
import br.com.vroom.repository.TagRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void init() {

        Tag a1 = Tag.builder().coordenada("A1").cor(null).disponivel(true).build();
        Tag a2 = Tag.builder().coordenada("A2").cor(CategoriaProblema.DOCUMENTACAO.getCorAssociada()).disponivel(false).build();
        Tag a3 = Tag.builder().coordenada("A3").cor(CategoriaProblema.ELETRICO.getCorAssociada()).disponivel(false).build();
        Tag a4 = Tag.builder().coordenada("A4").cor(CategoriaProblema.ESTETICO.getCorAssociada()).disponivel(false).build();
        Tag a5 = Tag.builder().coordenada("A5").cor(CategoriaProblema.SEGURANCA.getCorAssociada()).disponivel(false).build();
        
        Tag b1 = Tag.builder().coordenada("B1").cor(null).disponivel(true).build();
        Tag b2 = Tag.builder().coordenada("B2").cor(CategoriaProblema.MECANICO.getCorAssociada()).disponivel(false).build();
        Tag b3 = Tag.builder().coordenada("B3").cor(CategoriaProblema.MULTIPLO.getCorAssociada()).disponivel(false).build();
        Tag b4 = Tag.builder().coordenada("B4").cor(CategoriaProblema.CONFORME.getCorAssociada()).disponivel(false).build();
        Tag b5 = Tag.builder().coordenada("B5").cor(null).disponivel(true).build();       
        
        List<Tag> tags = List.of(a1, a2, a3, a4, a5, b1, b2, b3, b4, b5);
        
        tagRepository.saveAll(tags);


        Setor setorMecanico = Setor.builder().nome("Setor Mecânico").problemaRelacionado(CategoriaProblema.MECANICO).build();
        Setor setorEletrico = Setor.builder().nome("Setor Elétrico").problemaRelacionado(CategoriaProblema.ELETRICO).build();
        Setor setorDocumentacao = Setor.builder().nome("Setor de Documentação").problemaRelacionado(CategoriaProblema.DOCUMENTACAO).build();
        Setor setorEstetico = Setor.builder().nome("Setor Estético").problemaRelacionado(CategoriaProblema.ESTETICO).build();
        Setor setorSeguranca = Setor.builder().nome("Setor de Segurança").problemaRelacionado(CategoriaProblema.SEGURANCA).build();
        Setor setorConforme = Setor.builder().nome("Setor Conforme").problemaRelacionado(CategoriaProblema.CONFORME).build();
        Setor setorMultiplo = Setor.builder().nome("Setor Multiplo").problemaRelacionado(CategoriaProblema.MULTIPLO).build();

        List<Setor> setores = List.of(setorMecanico, setorEletrico, setorDocumentacao, setorEstetico, setorSeguranca, setorConforme, setorMultiplo);

        setorRepository.saveAll(setores);

        List<Moto> motos = List.of(
            Moto.builder().chassi("1A2B3C4D5E6F7G8H9").modelo(ModeloMoto.MOTTUPOP).placa("QAS0R12").problema(CategoriaProblema.MECANICO).setor(setorMecanico).tag(b2).build(),
            Moto.builder().chassi("9X8Y7Z6W5V4U3T2S1").modelo(ModeloMoto.MOTTUSPORT).placa("POI1T34").problema(CategoriaProblema.ELETRICO).setor(setorEletrico).tag(a2).build(),
            Moto.builder().chassi("1Q2W3E4R5T6Y7U8I9").modelo(ModeloMoto.MOTTUE).placa("DFG0H56").problema(CategoriaProblema.DOCUMENTACAO).setor(setorDocumentacao).tag(a2).build(),
            Moto.builder().chassi("A1B2C3D4E5F6G7H8I").modelo(ModeloMoto.MOTTUPOP).placa("JKL2M78").problema(CategoriaProblema.ESTETICO).setor(setorEstetico).tag(a4).build(),
            Moto.builder().chassi("P0O9I8U7Y6T5R4E3W").modelo(ModeloMoto.MOTTUSPORT).placa("ZXV3N90").problema(CategoriaProblema.SEGURANCA).setor(setorSeguranca).tag(a5).build(),
            Moto.builder().chassi("Z1X2C3V4B5N6M7A8S").modelo(ModeloMoto.MOTTUE).placa("QAW4S23").problema(CategoriaProblema.CONFORME).setor(setorConforme).tag(b4).build(),
            Moto.builder().chassi("W1X2Y3Z4A5B6C7D8T").modelo(ModeloMoto.MOTTUSPORT).placa("OPQ8W85").problema(CategoriaProblema.MULTIPLO).setor(setorMultiplo).tag(b3).build()
        );

        motoRepository.saveAll(motos);
    }
}
