package com.michelzarpelon.cursomcmz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michelzarpelon.cursomcmz.domain.Cidade;
import com.michelzarpelon.cursomcmz.domain.Cliente;
import com.michelzarpelon.cursomcmz.domain.Endereco;
import com.michelzarpelon.cursomcmz.domain.dto.ClienteDTO;
import com.michelzarpelon.cursomcmz.domain.dto.NovoClienteDTO;
import com.michelzarpelon.cursomcmz.domain.enums.TipoCliente;
import com.michelzarpelon.cursomcmz.repositories.CidadeRepository;
import com.michelzarpelon.cursomcmz.repositories.ClienteRepository;
import com.michelzarpelon.cursomcmz.repositories.EnderecoRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.DataIntegrityException;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorioObj;

	@Autowired
	private CidadeRepository repositorioObjCidade;

	@Autowired
	private EnderecoRepository repositorioObjEndereco;

	public Cliente find(Integer id) {
		Cliente cliente = repositorioObj.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado: " + id + ", Tipo do objeto: " + Cliente.class.getName());
		}
		return cliente;
	}

	public Cliente update(Cliente obj) {
		Cliente objBanco = find(obj.getId());
		obj.setCpfOuCnpj(objBanco.getCpfOuCnpj());
		obj.setTipo(objBanco.getTipo());
		return repositorioObj.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repositorioObj.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Objeto não pode ser deletado: " + id + ", Tipo do objeto: "
					+ Cliente.class.getName() + ", pois possui Entidades Relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repositorioObj.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorioObj.findAll(pageRequest);
	}

	/* converter ObrjetoDTO para objeto normal */
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(NovoClienteDTO objDTO) {
		Cliente objCli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()));
		Cidade objCid = repositorioObjCidade.findOne(objDTO.getCidadeId());
		Endereco objEnd = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), objCli, objCid);
		objCli.getEnderecos().add(objEnd);
		objCli.getTelefone().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			objCli.getTelefone().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			objCli.getTelefone().add(objDTO.getTelefone3());
		}
		return objCli;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repositorioObj.save(obj);
		repositorioObjEndereco.save(obj.getEnderecos());
		return obj;

	}

}
