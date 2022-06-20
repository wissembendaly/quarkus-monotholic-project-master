package org.starterProj.Service;

import lombok.extern.slf4j.Slf4j;
import org.starterProj.dto.CartDto;
import org.starterProj.entity.Cart;
import org.starterProj.entity.CartStatus;
import org.starterProj.repository.CartRepository;
import org.starterProj.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Slf4j
@ApplicationScoped
@Transactional
public class CartService {
    @Inject
    CartRepository cartRepository;

    @Inject
    CustomerRepository customerRepository;


    //Afficher tous les panier
    public List<CartDto> findAll(){
        log.debug("Resquest to get all carts");
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    //afficher les panier non confirmer
    public List<CartDto> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }
    //Cree un panier
    public Cart create(Long customerId) {
        if (this.getActiveCart(customerId) == null) {
            var customer =
                    this.customerRepository.findById(customerId).orElseThrow(() ->
                            new IllegalStateException("The Customer does not exist!"));
            var cart = new Cart(customer, CartStatus.NEW);
            return this.cartRepository.save(cart);
        } else {
            throw new IllegalStateException("There is already an active cart");
        }
    }

    //Associer un client au panier
    public CartDto createDto(Long customerId) {
        return mapToDto(this.create(customerId));
    }

    //Trouver un panier par l' ID de client s'il n'existe pas un exception va se d'eclencher grace à Transctionnal
    @Transactional(SUPPORTS)
    public CartDto findById(Long id) {
        log.debug("Request to get Cart : {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }

    //Effacer un panier
    public void delete(Long id) {
        log.debug("Request to delete Cart : {}", id);
        Cart cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id "
                        + id));
        cart.setStatus(CartStatus.CANCELED);
        this.cartRepository.save(cart);
    }

    //afficher les panier actives selon son propritère
    public CartDto getActiveCart (Long customerId){
        List<Cart> carts = this.cartRepository.findByStatusAndCustomerId(CartStatus.NEW, customerId);

        if (carts != null) {
            if (carts.size() == 1) {
                return mapToDto(carts.get(0));
            }
            if (carts.size() > 1) {
                throw new IllegalStateException("Many active carts detected !!!");
            }
        }
        return null;
    }

    public static CartDto mapToDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                CustomerService.mapToDto(cart.getCustomer()),
                cart.getStatus().name()
        );
    }
}
