<%@include file="navbar.html" %>
<%@include file="adminsidebar.html" %>
<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Book" %>
<%@page import="com.gcit.libsystem.service.AdminService" %>
<%-- <%
	AdminService service = new AdminService();
	Integer authorsCount = service.countAuthors();
	Integer numOfPages = 0;
	if (authorsCount % 10 > 0) {
		numOfPages = authorsCount / 10 + 1;
	} else {
		numOfPages = authorsCount / 10;
	}
	List<Author> authors = new ArrayList<>();
	Integer index = 1;
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = service.readAllAuthors(1);
	}
%>
${message}
<script>
	function searchAuthor(){
		$.ajax({
			url: "searchauthor",
			data:{
				searchName: $('#searchName').val()
			}
		}).done(function (data){
			$('#tableAndPages').html(data)
		})
	};
</script>
<script>
	function searchAuthorPage(pageNum){
		$.ajax({
			url: "searchauthorpage",
			data:{
				searchName: $('#searchName').val(),
				pageNum : pageNum
			}
		}).done(function (data){
			$('#tableAndPages').html(data)
		})
	}
</script> --%>
<%	
AdminService service = new AdminService();
List<Book> books = new ArrayList<>();
String authorName = null;
if (request.getAttribute("authors") != null) {
	books = (List<Book>) request.getAttribute("books");
} else {
	books = service.readAllBook();
}
%>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Book</title>

    <link href="./Dashboard Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
    <link href="./Dashboard Template for Bootstrap_files/dashboard.css" rel="stylesheet">

  </head>

  <body>

    <div class="container-fluid">
      <div class="row">        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h2 class="header">Book List</h2>	          
<!--           <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
    			<input type="text" class="form-control" placeholder="Search for..." name="searchName" 
    			id="searchName">
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
            	<button type="button" class="btn btn-primary" onclick="searchAuthor()">Submit �</button>
            </div>		
          </div> -->
          <div class="panel panel-default" id="tableAndPages">
			  <div class="panel-heading">
 				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editBookModel"
 					href="addbook.jsp">Add Book</button>
<%--            		<div class="pull-right" id="page-selection">
					<nav aria-label="Page navigation">
						<ul class="pagination" style="margin: 0px !important; vertical-align: middle;">
							<li><a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<% for (int i = 1; i <= numOfPages; i++) { %>
								<li><a href="pageauthors?pageNo=<%=i%>"><%=i%></a></li>
							<% } %>
							<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>
				</div> --%>         				  
			  </div>
			
			  <table class="table table-striped">
			    <thead>
                <tr>
                  <th><h4><span class="label label-primary">#</span></h4></th>
                  <th><h4><span class="label label-primary">Book Title</span></h4></th>
                  <th><h4><span class="label label-primary">Author</span></h4></th>
                  <th><h4><span class="label label-primary">Publisher</span></h4></th>
                  <th><h4><span class="label label-primary">Genre</span></h4></th>
                  <th><h4><span class="label label-primary">Edit</span></h4></th>
                  <th><h4><span class="label label-primary">Delete</span></h4></th>
                </tr>
              </thead>
              <tbody>
<%-- 				<% if(request.getAttribute("index") != null){
					tableIndex = (Integer) request.getAttribute("index");
              		}
              	%> --%>
				<% for (Book book : books) { %>
                <tr>
                  <td><%=books.indexOf(book) + 1 %></td>
                  <td><%=book.getTitle() %></td>
                  <td><%=service.parseListItems(book.getAuthors())%></td>
                  <td><%=book.getPublisher().getPublisherName()%></td>
                  <td><%=service.parseListItems(book.getGenres())%></td>                  
                  <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editBookModel"
						href="editbook.jsp?bookId=<%=book.getBookId()%>">Update</button></td>
                  <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#editBookModel"
						href="deletebook.jsp?bookId=<%=book.getBookId()%>">Delete</button></td>
                </tr>
               	<% } %>
              </tbody>        
			 </table>
		  </div>
        </div>
      </div>
    </div>

<div class="modal fade" id="editBookModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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