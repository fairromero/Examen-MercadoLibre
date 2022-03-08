package com.examen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.examen.dto.Adn;
import com.examen.model.Persona;
import com.examen.repository.PersonaRepository;
import com.examen.service.PersonaService;
import com.examen.service.impl.PersonaServiceImpl;
import com.examen.utils.ValidadorPersona;

public class ValidadorPersonaTest  {

    ValidadorPersona validadorPersona = new ValidadorPersona();

    @TestConfiguration
    static class PersonaServiceImplTestContextConfiguration {

        @Bean
        public PersonaService personaService() {
            return new PersonaServiceImpl();
        }
    }

    @Autowired
    private PersonaService personaService;

    @MockBean
    private PersonaRepository personaRepository;

    @Test
    public void isMutanteTrue() {
        Adn adn = new Adn();
        List<String> ListAdn = new ArrayList<>();
        ListAdn.add("ATGCGA");
        ListAdn.add("CAGTGC");
        ListAdn.add("TTATGT");
        ListAdn.add("AGAAGG");
        ListAdn.add("CCCCTA");
        ListAdn.add("TCACTG");
        adn.setDna(ListAdn);

        Persona p = validadorPersona.isMutante(adn);

        //dna para analisis
        String dna = "[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]";
        Persona persona = new Persona();
        persona.setAdn(dna);
        persona.setMutante(true);

        assertEquals(persona.getAdn(), p.getAdn());
    }

    @Test
    public void isMutanteFalse() {
        Adn adn = new Adn();
        List<String> ListAdn = new ArrayList<>();
        ListAdn.add("CTGCGA");
        ListAdn.add("CAGTGC");
        ListAdn.add("TTATGT");
        ListAdn.add("AGAAGG");
        ListAdn.add("ACCCTA");
        ListAdn.add("TCACTG");
        adn.setDna(ListAdn);

        Persona p = validadorPersona.isMutante(adn);

        //Persona de referencia
        String dna = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona persona = new Persona();
        persona.setAdn(dna);
        persona.setMutante(false);

        assertEquals(persona.getAdn(), p.getAdn());
        assertEquals(persona.getMutante(), p.getMutante());
    }

    @Test
    public void guardarPersonaNueva() {

        String adn = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona personaGuardar = new Persona();
        personaGuardar.setAdn(adn);
        personaGuardar.setMutante(false);

        Mockito.when(personaRepository.findByAdn(personaGuardar.getAdn())).thenReturn(null);

        Persona found = personaService.findById(personaGuardar.getAdn());

        Mockito.when(personaRepository.save(personaGuardar)).thenReturn(personaGuardar);

        Persona saved = personaService.savePersona(personaGuardar);

        Persona p = validadorPersona.guardarPersona(personaGuardar);

        assertEquals(personaGuardar, p);
    }

    @Test
    public void guardarPersonaExistente() {

        String adn = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona personaGuardar = new Persona();
        personaGuardar.setAdn(adn);
        personaGuardar.setMutante(false);

        Mockito.when(personaRepository.findByAdn(personaGuardar.getAdn())).thenReturn(personaGuardar);

        Persona found = personaService.findById(personaGuardar.getAdn());

        Persona p = validadorPersona.guardarPersona(personaGuardar);

        assertEquals(personaGuardar, p);

    }
}