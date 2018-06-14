
/**
 * 
 */

package com.javateam.model.jpavo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author oracle
 *
 */
@Entity
@Table(name="board_tbl")
@Data

/*@SqlResultSetMapping(name="insertOrUpdateResult", 
					 columns = { @ColumnResult(name = "count")})
	@NamedNativeQueries({
		@NamedNativeQuery(
			name = "updateBoard",
			query = "UPDATE board_tbl SET "
					  + "board_subject=?,"
					  + "board_pass=?,"
					  + "board_file=?,"
					  + "board_date=?,"
					  + "board_content=? "
					  + "WHERE board_num=?"
			,resultSetMapping = "insertOrUpdateResult"
		),
		
		@NamedNativeQuery(
				name = "insertBoard",
				query = "INSERT INTO board_tbl VALUES "
				      + "(?,?,?,?,?,?,?,?,?,?,?)"
				,resultSetMapping = "insertOrUpdateResult"
			)
})*/
public class BoardVO {
	
	@Id
	@Column(name="board_num")
	@GeneratedValue(strategy = GenerationType.AUTO) 
	// hibernate_sequence.nextval
	private int boardNum; // 글 번호
	
	@Column(name="board_name")
	private String boardName; // 글 작성자
	
	@Column(name="board_pass")
	private String boardPass; // 글 비밀번호
	
	@Column(name="board_subject")
	private String boardSubject; // 글 제목
	
	@Column(name="board_content")
	private String boardContent; // 글 내용
	
	@Column(name="board_file")
	private String boardFile; // 첨부 파일
	
	@Column(name="board_re_ref")
	private int boardReRef; // 관련글 번호
	
	@Column(name="board_re_lev")
	private int boardReLev; // 답글 레벨
	
	@Column(name="board_re_seq")
	private int boardReSeq; // 관련글 중 출력 순서
	
	@Column(name="board_read_count")
	private int boardReadCount = 0; // 조회수
	
	@Column(name="board_date")
	private Date boardDate; // 작성일

}