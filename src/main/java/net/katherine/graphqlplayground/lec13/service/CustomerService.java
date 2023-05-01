package net.katherine.graphqlplayground.lec13.service;

import net.katherine.graphqlplayground.lec13.dto.CustomerDto;
import net.katherine.graphqlplayground.lec13.dto.DeleteResponseDto;
import net.katherine.graphqlplayground.lec13.dto.Status;
import net.katherine.graphqlplayground.lec13.repository.CustomerRepository;
import net.katherine.graphqlplayground.lec13.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Flux<CustomerDto> allCustomers() {
        return customerRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> customerById(Integer id) {
        return customerRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto) {
        return Mono.just(dto)
                .map(EntityDtoUtil::toEntity)
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
        return customerRepository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(id, dto))
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id)
                .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
    }

}
