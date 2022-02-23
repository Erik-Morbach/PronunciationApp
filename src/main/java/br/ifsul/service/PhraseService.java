package br.ifsul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsul.repository.PhraseRepository;

@Service
public class PhraseService {
	@Autowired
	private PhraseRepository phraseRepository;
}
