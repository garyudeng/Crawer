package com.bimoku.integrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookAmazon;
import com.bimoku.common.bean.BookDB;
import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.common.bean.BookJD;
import com.bimoku.repository.dao.BookDBDao;
import com.bimoku.repository.dao.BookDDDao;
import com.bimoku.repository.dao.BookJDDao;

@Service("dbIntegrated")
public class DBIntegrated extends Integrated{
	
	@Autowired
	BookDBDao bookDBDao;
	
	@Override
	protected BookDetail putDataIntoDetail(BookDetail detail) {
		BookDB bd = new BookDB();
		bd.setIsbn(detail.getIsbn());
		bd = bookDBDao.searchOne(bd);
		
		//判断是否存在
		if(bd != null){
			BookDB bbd = (BookDB) detail;
			bbd.setId(bd.getId());
			//跟新操作
			return bookDBDao.update(bbd);
		}
		return bookDBDao.save((BookDB)detail);
	}

	@Override
	protected Book filterFileds(Book book, BookDetail detail) {
		//TODO
		return book;
	}
}
