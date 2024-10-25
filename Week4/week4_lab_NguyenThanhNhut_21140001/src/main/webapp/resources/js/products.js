function fetchAllProducts() {
  const productListDiv = document.getElementById('productList');

  fetch('http://localhost:8080/week3_lab-1.0-SNAPSHOT/api/products')
      .then(response => response.json())
      .then(
          data => {
            productListDiv.innerHTML = '';

            data.forEach(
                product => {
                  productListDiv.innerHTML +=  `
                    <div class="col-md-3">
                      <div class="card mb-3">
                        <a href="http://localhost:8080/week3_lab-1.0-SNAPSHOT/products/${product.productId}">
                            <img src="${product.imgPath}" class="card-img-top" alt="${product.name}" style="height: 200px; object-fit: contain;">
                        </a>
                        <div class="card-body">
                          <h5 class="card-title">${product.name}</h5>
                          <p class="card-text">${product.description}</p>
                          <p class="card-text">Price: <strong>${formatter.format(product.price)}</strong></p>
                        </div>
                        <div class="card-footer d-flex justify-content-between">
                          <a href="http://localhost:8080/week3_lab-1.0-SNAPSHOT/controller?action=addPrice&productId=${product.productId}" class="btn btn-success">Add Price</a>
                          <a href="http://localhost:8080/week3_lab-1.0-SNAPSHOT/controller?action=edit&productId=${product.productId}" class="btn btn-warning">Edit</a>
                          <a href="/products/${product.id}/delete" class="btn btn-danger">Delete</a>
                        <div>
                      </div>
                    </div>
                  `
                }
            )
          }
      ).catch(error => {
      productListDiv.innerHTML = `
        <div class="alert alert-danger">Error fetching products: ${error.message}</div>
      `;
  });
}

const formatter = new Intl.NumberFormat('vi-VN', {
  style: 'currency',
  currency: 'VND',
});

document.addEventListener('DOMContentLoaded', fetchAllProducts);
