<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.*"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	List<Author> authors = service.readAllAuthors(null);
	List<Genre> genres = service.readAllGenre();
	List<Publisher> publisher = service.readAllPublisher();
%>
<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Book Details</h4>
      </div>
      <form action="addbook" method="post">
	      <div class="modal-body">
			<p>Enter the name of your book:</p>
			<input type="text" class="form-control" name="bookName" placeholder="Book name">
			<p>Select the author of your book:</p>
			<select id="authorList" multiple="multiple" name="authorIDs">
				<% for (int i = 0; i < authors.size(); i++) { %>
				<option value="<%=authors.get(i).getAuthorID()%>"><%=authors.get(i).getAuthorName()%></option>
			    <% } %>
			</select>
			<p>Select the publisher of your book:</p>
			<select id="publisherList" name="publisherID">
				<% for (int i = 0; i < publisher.size(); i++) { %>
				<option value="<%=publisher.get(i).getPublisherId()%>"><%=publisher.get(i).getPublisherName()%></option>
			    <% } %>
			</select>
			<p>Select the genre of your book:</p>
			<select id="genreList" multiple="multiple" name="genreIDs">
				<% for (int i = 0; i < genres.size(); i++) { %>
				<option value="<%=genres.get(i).getGenreId()%>"><%=genres.get(i).getGenreName()%></option>
			    <% } %>
			</select>				     	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#authorList').multiselect();
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#genreList').multiselect();
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#publisherList').multiselect();
    });
</script>

      
      