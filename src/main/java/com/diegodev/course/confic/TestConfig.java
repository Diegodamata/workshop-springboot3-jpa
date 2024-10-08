package com.diegodev.course.confic;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diegodev.course.entities.Category;
import com.diegodev.course.entities.Order;
import com.diegodev.course.entities.OrderItem;
import com.diegodev.course.entities.Payment;
import com.diegodev.course.entities.Product;
import com.diegodev.course.entities.User;
import com.diegodev.course.entities.enums.OrderStatus;
import com.diegodev.course.repositories.CategoryResository;
import com.diegodev.course.repositories.OrderItemRepository;
import com.diegodev.course.repositories.OrderRepository;
import com.diegodev.course.repositories.ProductRepository;
import com.diegodev.course.repositories.UserRepository;

@Configuration //indica que essa classe deve ser utilizada durante a fase de configuração da aplicação
@Profile("test") //significa que essa configuração será aplicada somente quando a aplicação estiver rodando 
//no perfil de ambiente de teste. Isso é útil para criar diferentes configurações para diferentes ambientes, como dev, test e prod.
public class TestConfig implements CommandLineRunner{ //A interface CommandLineRunner permite que você execute um código específico logo após a inicialização da aplicação.

	@Autowired //minha injeção de dependencia, com essa anotação o spring injeta automaticamente a instancia do userRepository
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository; 
	
	@Autowired
	private CategoryResository categoryResository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepositoty;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		
		categoryResository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//no modelo orientado a objeto estou chamando o product e adicionando as categorias de cada produto
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//salvando novamente no bando de dados, porem dessa vez vai ser salvo na tabela tb_product_category
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1); 
		
		userRepository.saveAll(Arrays.asList(u1, u2)); //chamo o userRepository ele chama o metodo da interface JpaRepository, para salvar no banco de dados
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepositoty.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		//colocando um pagamento para o pedido o1
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		//para salvar o objeto dependente não precisa criar um repository para ele 
		//crio uma assiciação de mão dupla qunado quando as duas entidade relacionadas e cada uma mantém uma referência para a outra
		//apenas chama o pedido e passo o pagamento desse pedido, associando os dois, assim o pedido conhece o pagamento, e o pagamento conhece o pedido
		o1.setPayment(pay1);
		
		orderRepository.save(o1); //apos assicionar o pagamento ao pedido eu salvo novamente o pedido
	}
}
