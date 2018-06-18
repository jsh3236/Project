/**
 * 
 */
package com.javateam.service.deprecated;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.model.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author oracle
 *
 */
@Repository
@Slf4j
@Transactional
public class JpaDAOImpl implements JpaDAO {
	
    @PersistenceContext
	private EntityManager entityManager;
    
	@Autowired
    private JpaTransactionManager transactionManager;
   
    /**
	 * @see com.javateam.service.deprecated.springBoard.repository.JpaDAO#insert(com.javateam.model.vo.springBoard.domain.vo.BoardVO)
	 */
    @Override
	public void insert(BoardVO board) {
		
		log.info("insert");
		
	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	 
        TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			// entityManager.getTransaction().begin(); // (X)
			
			// native query
			Query query = entityManager.createNativeQuery("INSERT INTO board_tbl VALUES "
										  				  + "(hibernate_sequence.nextval,"
										  				  + "?,?,?,?,?,?,?,?)");
			query.setParameter(1, board.getBoardContent())
				 .setParameter(2, board.getBoardDate())
				 .setParameter(3, board.getBoardFile())
				 .setParameter(4, board.getBoardReLev())
				 .setParameter(5, board.getBoardReRef())
				 .setParameter(6, board.getBoardReSeq())
				 .setParameter(7,board.getBoardReadCount())
				 .setParameter(8,board.getBoardSubject());
			
			log.info("저장 성공 ? {}", query.executeUpdate()==1 ? true : false);
			
			// entityManager.merge(board); 
			// entityManager.persist(board); // (X) not good here ! no execution !
			// entityManager.getTransaction().commit(); // (X)
			
			/*board = entityManager.find(BoardVO.class, board.getBoardNum());
	        entityManager.refresh(board);
	        log.info("vo {}", board);*/
	        
	        transactionManager.commit(status); // Spring transaction commit
	        
		} catch (Exception e) {
			
			log.info("error : {}",e);
			
			// entityManager.getTransaction().rollback(); // (X)
			transactionManager.rollback(status); // Spring transaction rollback
			e.getStackTrace();
		} //
		
	} //

	/**
	 * @see com.javateam.service.deprecated.springBoard.repository.JpaDAO#list()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> list() {
		
		log.info("select All");
		
		/*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<BoardVO> cq = builder.createQuery(BoardVO.class);
        Root<BoardVO> root = cq.from(BoardVO.class);
        cq.select(root);
        
        return (List<BoardVO>) entityManager.createQuery(cq)
        									.getResultList();*/
		
		return (List<BoardVO>) entityManager.createNativeQuery("SELECT * FROM board_tbl", 
														 	   BoardVO.class)
											.getResultList();
	} //
	
    /**
	 * @see com.javateam.service.deprecated.springBoard.repository.JpaDAO#update(com.javateam.model.vo.springBoard.domain.vo.BoardVO)
	 */
	@Override
	public void update(BoardVO board) {

		log.info("update");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        
		try {
			entityManager.merge(board);
			transactionManager.commit(status);	
		} catch (Exception e) {
			log.info("error");
			transactionManager.rollback(status);
		} // try
		
	} //
	
    /**
	 * @see com.javateam.service.deprecated.springBoard.repository.JpaDAO#delete(int)
	 */
	@Override
	public boolean delete(int boardNum) {
		
		log.info("delete");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        
        BoardVO board = entityManager.find(BoardVO.class, boardNum);
        // PK만으로는 삭제안됨.
        
        log.info("board {}",board);
        
		try {
			entityManager.remove(board);
			transactionManager.commit(status);	
		} catch (Exception e) {
			log.info("error");
			transactionManager.rollback(status);
			return false;
		} // try
		
		return true;
		
	} //

	/* (non-Javadoc)
	 * @see com.javateam.springBoard.repository.JpaDAO#get(int)
	 */
	@Override
	public BoardVO get(int boardNum) {

		log.info("get");
		
		return entityManager.find(BoardVO.class, boardNum);
	}

}