<%@include file="navbar.html" %>
<%@include file="adminsidebar.html" %>
<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch" %>
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
List<Branch> branchs = new ArrayList<>();
if (request.getAttribute("branchs") != null) {
	branchs = (List<Branch>) request.getAttribute("branchs");
} else {
	branchs = service.readAllBranchs();
}
%>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Library Branch</title>

    <link href="./Dashboard Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
    <link href="./Dashboard Template for Bootstrap_files/dashboard.css" rel="stylesheet">

  </head>

  <body>

    <div class="container-fluid">
      <div class="row">        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h2 class="header">Library Branch List</h2>          
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
 				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editBranchModel"
 					href="addbranch.jsp">Add Library Branch</button>
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
                  <th><h4><span class="label label-primary">Library Branch Name</span></h4></th>
                  <th><h4><span class="label label-primary">Library Branch Address</span></h4></th>
                  <th><h4><span class="label label-primary">Edit</span></h4></th>
                  <th><h4><span class="label label-primary">Delete</span></h4></th>
                </tr>
              </thead>
              <tbody>
<%-- 				<% if(request.getAttribute("index") != null){
					tableIndex = (Integer) request.getAttribute("index");
              		}
              	%> --%>
				<% for (Branch branch : branchs) {%>
                <tr>
                  <td><%=branchs.indexOf(branch) + 1 %></td>
                  <td><%=branch.getBranchName()%></td>
                  <td><%=branch.getBranchAddress()%></td>
                  <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editBranchModel"
						href="editbranch.jsp?branchId=<%=branch.getBranchID()%>">Update</button></td>
                  <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#editBranchModel"
						href="deletebranch.jsp?branchId=<%=branch.getBranchID()%>">Delete</button></td>
                </tr>
               	<% } %>
              </tbody>        
			 </table>
		  </div>
        </div>
      </div>
    </div>

<div class="modal fade" id="editBranchModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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