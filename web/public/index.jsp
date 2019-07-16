<%@include file="cabecalho.jsp" %>
<div class="breadcome-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="breadcome-list">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="breadcome-heading">
                                <form role="search" class="">
                                    <input type="text" placeholder="Search..." class="form-control">
                                    <a href=""><i class="fa fa-search"></i></a>
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <ul class="breadcome-menu">
                                <li><a href="#">Loja</a> <span class="bread-slash">/</span>
                                </li>
                                <li><span class="bread-blod">Livros</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="product-new-list-area">
    <div class="container-fluid">
        <div class="row">
            <c:forEach items="${lista}" var="obj">
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                    <div class="single-new-trend mg-t-30" style="height: 300px;">
                        <a href="LivroWS?acao=detalhe&id=${obj.id}">
                            <img src="../arquivos/${obj.endFoto1}" style="max-height: 250px;width: auto; margin: auto;" alt="">
                        </a>
                        <div class="overlay-content">
                            <a href="LivroWS?acao=detalhe&id=${obj.id}">
                                <h2>R$ ${obj.valor}</h2>
                            </a>
                            <!--<a href="#" class="btn-small">Now</a>-->
                            <div class="product-action">
                                <ul style="background-color: white;">
                                    <li>
                                        <a data-toggle="tooltip" title="Shopping" href="#"><i class="fa fa-shopping-bag" aria-hidden="true"></i></a>
                                    </li>
                                    <li>
                                        <a data-toggle="tooltip" title="Quick view" href="#"><i class="fa fa-heart" aria-hidden="true"></i></a>
                                    </li>
                                </ul>
                            </div>
                            <a href="LivroWS?acao=detalhe&id=${obj.id}">
                                <h4>${obj.titulo}</h4>
                            </a>
                            <div class="pro-rating">
                                <i class="fa fa-star color"></i>
                                <i class="fa fa-star color"></i>
                                <i class="fa fa-star color"></i>
                                <i class="fa fa-star color"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>
        <div>
            <br>
        </div>
        <div class="card-footer">
        <c:if test = "${not empty msg}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
		</button>
                <strong>Sucesso!</strong> ${msg}
            </div>
        </c:if>
        </div>
    </div>
</div>

<%@include file="rodape.jsp" %>