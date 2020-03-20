package com.arobs.project.bookRent;

import com.arobs.project.book.BookService;
import com.arobs.project.copy.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rentManager")
public class RentManager {
    private BookRentService bookRentService;
    private BookService bookService;
    private CopyService copyService;


    @Autowired
    public RentManager(BookRentService bookRentService, BookService bookService, CopyService copyService) {
        this.bookRentService = bookRentService;
        this.bookService = bookService;
        this.copyService = copyService;
    }

//    public BookRentDTO insertBookRent(BookRentDTO bookRentDTO) throws ValidationException, ParseException {
//        BookDTO foundBook = bookService.findById(bookRentDTO.getBookId());
//        List<CopyDTO> foundCopiesForBook=copyService.findCopiesForBook(foundBook.getId());
//        List<CopyDTO> foundAvailableCopies= foundCopiesForBook //is it better to do that from query?
//                .stream()
//                .filter(c->c.getCopyStatus().equals("available"))
//                .collect(Collectors.toList());
//        if(foundAvailableCopies.isEmpty()){
//          throw new ValidationException("No available books found!");
//        }
////        BookRent bookRentToInsert= ProjectModelMapper.convertDTOtoBookRent(bookRentDTO);
////        bookRentToInsert.setCopy(ProjectModelMapper.convertDTOtoCopy(foundAvailableCopies.get(0)));
//        bookRentDTO.s
//        return bookRentService.insertBoorRent(bookRentToInsert);
//    }
}
