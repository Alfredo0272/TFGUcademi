package tfg.cervecera.aplication.factory;

import org.springframework.stereotype.Service;

import tfg.cervecera.dto.factory.FactoryDTO;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.repositorys.FactoryRepository;

@Service
public class FactoryServices {
	
	private final FactoryRepository factoryRepository;
	
	public FactoryServices(FactoryRepository factoryRepository) {
		this.factoryRepository = factoryRepository;
	}

	public void createFactory(FactoryDTO dto) {
		
		String name = dto.getName();
		if (factoryRepository.existsByName(name)) {
			throw new IllegalArgumentException("La fábrica ya existe");
		}
		
		Factory factory = new Factory();
		factory.setName(dto.getName());
		factory.setLocation(dto.getLocation());
		factory.setCapacity(dto.getCapacity());
		
		factoryRepository.save(factory);
	}
	

}
