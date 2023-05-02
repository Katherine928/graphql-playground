package net.katherine.graphqlplayground.lec16.serverapp.service;

import net.katherine.graphqlplayground.lec16.dto.*;
import net.katherine.graphqlplayground.lec16.serverapp.repository.CustomerRepository;
import net.katherine.graphqlplayground.lec16.serverapp.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEventService customerEventService;

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
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> customerEventService.emitEvent(CustomerEvent.create(c.getId(), Action.CREATED)));
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
        return customerRepository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(id, dto))
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> customerEventService.emitEvent(CustomerEvent.create(c.getId(), Action.UPDATED)));
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id)
                .doOnSuccess(c -> customerEventService.emitEvent(CustomerEvent.create(id, Action.DELETED)))
                .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
    }

}
