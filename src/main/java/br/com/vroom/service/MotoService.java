package br.com.vroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vroom.model.Moto;
import br.com.vroom.repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> listarMotos(String ordem) {
        if ("problema".equals(ordem)) {
            return motoRepository.findAllByOrderByProblemaAsc();
        } else if ("modelo".equals(ordem)) {
            return motoRepository.findAllByOrderByModeloAsc();
        }

        return motoRepository.findAll();
    }

}
