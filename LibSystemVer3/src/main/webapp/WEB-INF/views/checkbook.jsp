<%@include file="navbar.html" %>
<%@include file="borrowersidebar.html" %>
<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Book" %>
<%@page import="com.gcit.libsystem.entity.Branch" %>
<%@page import="com.gcit.libsystem.service.BorrowerService" %>

<%	
	BorrowerService service = new BorrowerService();
	List<Book> books = new ArrayList<>();
	Integer branchID = Integer.parseInt(request.getAttribute("branchId").toString());
	Integer borrowerID = Integer.parseInt(request.getAttribute("borrowerId").toString());
	Branch branch = service.readBranchWithLimit(branchID);
	books = branch.getBooks();
%>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Check Book</title>

    <link href="./Dashboard Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
    <link href="./Dashboard Template for Bootstrap_files/dashboard.css" rel="stylesheet">

  </head>

  <body>

    <div class="container-fluid">
    
      <div class="row">        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h2 class="header">Book List</h2>	          
          <div class="panel panel-default" id="tableAndPages">
			  <div class="panel-heading">
			  </div> 
			  <table class="table table-striped">
			    <thead>
                <tr>
                  <th><h4><span class="label label-primary">#</span></h4></th>
                  <th><h4><span class="label label-primary">Book Title</span></h4></th>
                  <th><h4><span class="label label-primary">Author</span></h4></th>
                  <th><h4><span class="label label-primary">Publisher</span></h4></th>
                  <th><h4><span class="label label-primary">Genre</span></h4></th>
                  <th><h4><span class="label label-primary">Library Branch</span></h4></th>
                  <th><h4><span class="label label-primary">Num of Copies</span></h4></th>
                  <th><h4><span class="label label-primary">Check Out</span></h4></th>
                </tr>
              </thead>
              <tbody>
				<% for (Book book : books) { %>
                <tr>
                  <td><%=books.indexOf(book) + 1 %></td>
                  <td><%=book.getTitle()%></td>
                  <td><%=service.parseListItems(book.getAuthors())%></td>
                  <td><%=book.getPublisher().getPublisherName()%></td>
                  <td><%=service.parseListItems(book.getGenres())%></td>
				  <td><%=branch.getBranchName()%></td>
				  <td><%=branch.getnoOfCopies(book.getBookId())%></td>
                  <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#checkBookModel"
						href="confirmcheck.jsp?bookId=<%=book.getBookId()%>&branchId=<%=branch.getBranchID()%>&bwId=<%=borrowerID%>">
						Check Out</button></td>
                </tr>
               	<% } %>
              </tbody>        
			 </table>
		  </div>
        </div>
      </div>
    </div>

<div class="modal fade" id="checkBookModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    </div>
  </div>
</div>

<script>
$(document).ready(function()
{
    $('.modal').on('hidden.bs.modal', function(e)
    { 
        $(this).removeData();
    }) ;
});
</script>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./Dashboard Template for Bootstrap_files/jquery.min.js.download"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./Dashboard Template for Bootstrap_files/bootstrap.min.js.download"></script>
    <script src="./Dashboard Template for Bootstrap_files/holder.min.js.download"></script>
	<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>

  

</body></html>