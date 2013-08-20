package com.bimoku.integrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.common.bean.BookJD;
import com.bimoku.repository.dao.BookDDDao;
import com.bimoku.repository.dao.BookJDDao;

@Service("jdIntegrated")
public class JDIntegrated extends Integrated{
	
	@Autowired
	BookJDDao bookJDDao;
	
	@Override
	protected BookDetail putDataIntoDetail(BookDetail detail) {
		BookJD dd = new BookJD();
		dd.setIsbn(detail.getIsbn());
		dd = bookJDDao.searchOne(dd);
		
		//判断是否存在
		if(dd != null){
			BookJD bdd = (BookJD) detail;
			bdd.setId(dd.getId());
			//跟新操作
			return bookJDDao.update(bdd);
		}
		return bookJDDao.save((BookJD)detail);
	}

	@Override
	protected Book filterFileds(Book book, BookDetail detail) {
		return book;
	}
}
