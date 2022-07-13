package com.lsd.logement.security.services;

import com.lsd.logement.dao.UserRepository;
import com.lsd.logement.entity.personnel.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Recherche et chargement des parametres de l'utilisateur a partir de son adresse email
	 * @param username Adresse email de l'utilisateur
	 * @return Un objet UserDetails contenant les parametre d'authentification de l'utilisateur
	 * @throws UsernameNotFoundException Erreur : utilisateur introuvable
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if(user == null){
			throw new UsernameNotFoundException("Utilisateur inexistant: " + username);
		}

		return UserDetailsImpl.build(user);
	}

}
