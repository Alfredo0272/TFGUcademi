package tfg.cervecera.aplication.factory;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import tfg.cervecera.dto.factory.FactoryDTO;
import tfg.cervecera.dto.factory.FactoryRegisterDTO;
import tfg.cervecera.exceptions.InvalidDataException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.repositorys.CompanyRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;
import tfg.cervecera.config.SecurityUtils;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;
    private final CompanyRepository companyRepository;

    public FactoryService(FactoryRepository factoryRepository, 
                         CompanyRepository companyRepository) {
        this.factoryRepository = factoryRepository;
        this.companyRepository = companyRepository;
    }

    public FactoryDTO createFactory(FactoryRegisterDTO dto) {
        validateFactoryRegisterDTO(dto);
        Long companyId = SecurityUtils.getCurrentCompanyId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company no encontrada"));

        Factory factory = new Factory();
        factory.setName(dto.getName());
        factory.setLocation(dto.getLocation());
        factory.setCapacity(dto.getCapacity());
        factory.setCompany(company);

        Factory saved = factoryRepository.save(factory);
        return mapToDTO(saved);
    }

    public FactoryDTO updateFactory(Long id, FactoryRegisterDTO dto) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Factory no encontrada"));

        Long companyId = SecurityUtils.getCurrentCompanyId();
        if (!factory.getCompany().getId().equals(companyId)) {
            throw new IllegalArgumentException("No tienes permiso para modificar esta fábrica");
        }

        if (dto.getName() != null) factory.setName(dto.getName());
        if (dto.getLocation() != null) factory.setLocation(dto.getLocation());
        if (dto.getCapacity() != null) factory.setCapacity(dto.getCapacity());

        Factory updated = factoryRepository.save(factory);
        return mapToDTO(updated);
    }

    public List<FactoryDTO> findAll() {
        Long companyId = SecurityUtils.getCurrentCompanyId();
        return factoryRepository.findById(companyId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public FactoryDTO findById(Long id) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Factory no encontrada"));

        Long companyId = SecurityUtils.getCurrentCompanyId();
        if (!factory.getCompany().getId().equals(companyId)) {
            throw new IllegalArgumentException("No tienes permiso para ver esta fábrica");
        }

        return mapToDTO(factory);
    }

    public void deleteFactory(Long id) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Factory no encontrada"));

        Long companyId = SecurityUtils.getCurrentCompanyId();
        if (!factory.getCompany().getId().equals(companyId)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar esta fábrica");
        }

        factoryRepository.delete(factory);
    }

    private void validateFactoryRegisterDTO(FactoryRegisterDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDataException("El nombre de la fábrica no puede estar vacío");
        }
        if (dto.getLocation() == null || dto.getLocation().isEmpty()) {
            throw new InvalidDataException("La ubicación de la fábrica no puede estar vacía");
        }
        if (dto.getCapacity() == null || dto.getCapacity() <= 0) {
            throw new InvalidDataException("La capacidad debe ser mayor que 0");
        }
    }

    private FactoryDTO mapToDTO(Factory factory) {
        return new FactoryDTO(
                factory.getId(),
                factory.getName(),
                factory.getLocation(),
                factory.getCapacity(),
                factory.getCompany().getId(),
                factory.getCompany().getName()
        );
    }
}