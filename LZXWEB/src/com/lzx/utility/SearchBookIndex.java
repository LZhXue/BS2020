package com.lzx.utility;

import com.lzx.model.Book_Shelf;
import com.lzx.model.Recog;

public class SearchBookIndex {

	public static Book_Shelf getBookInfoSearch(String indexNnumber){
		Book_Shelf model = Recog.Recognize(indexNnumber);
		if(model.getArea_number().equals("��ͥһ��")) {
			model.setSearch_information(model.getStorey_number()+"-A-"+model.getShelf_number());	
		}else if(model.getArea_number().equals("��ͥ����")) {
			model.setSearch_information(model.getStorey_number()+"-B-"+model.getShelf_number());
		}else if(model.getArea_number().equals("��ͥ����")) {
			model.setSearch_information(model.getStorey_number()+"-C-"+model.getShelf_number());
		}else if(model.getArea_number().equals("��һ��") ){
			model.setSearch_information(model.getStorey_number()+"-D-"+model.getShelf_number());
		}else if(model.getArea_number().equals("��һ��")) {
			model.setSearch_information(model.getStorey_number()+"-E-"+model.getShelf_number());
		}
		return model;
		
	}
}
