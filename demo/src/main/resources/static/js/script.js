function access() {
  var ifr = document.getElementById('iframe_id');
  var ifr_doc = ifr.contentWindow.document;
  var check_click_add = ifr_doc.getElementsByClassName('them');
  var check_click_link = ifr_doc.getElementsByTagName('a');
  for (var i = 0; i < check_click_add.length; i++) {
    check_click_add[i].onclick = function() {
      mo_tbdn(this);
    }
  }
  for (var i = 0; i < check_click_link.length; i++) {
    check_click_link[i].onclick = function() {
      mo_tbdn(this);
    }
  }
  var so_trang = ifr_doc.getElementsByClassName('so_trang');
  for (var i = 0; i < so_trang.length - 1; i++) {
    so_trang[i].onclick = function(){
      scroll_to_top();
    }
  }

};

function mo_tbdn(item) {
  var quaylai = document.getElementById('no');
  var dangnhap = document.getElementById('yes');
  var tbdn = document.getElementById('thong-bao-dang-nhap');
  var url = item.getAttribute('href');
  var get_class = item.getAttribute('class');
  var so_trang = item.getAttribute('class','so_trang');
  if (url != '#' && url != null && url != '' && get_class != 'so_trang') {
    window.location = url;
  }
  if (get_class == 'them') {
    tbdn.style.display = 'block';
    quaylai.onclick = function() {
      tbdn.style.display = 'none';
    }
    dangnhap.onclick = function() {
      window.location = 'dangnhap';
    }
  }
};


function change_iframe(item) {
  var buttons = document.querySelectorAll('.dm');
  buttons.forEach(function(button) {
    button.classList.remove('menu-ben-trai-selected');
  });

  // Thêm class selected cho button được nhấp
  item.classList.add('menu-ben-trai-selected');
  window.location.href = item.value;

}
function tangCart(cartItemId) {
  $.ajax({
    type: 'POST',
    url: '/tangcart', // Đường dẫn đến controller
    data: {
      cartItemId: cartItemId
    },
    success: function (response) {
      // Xử lý dữ liệu trả về nếu cần
      console.log(response);
      // Không cần làm gì nếu không muốn xử lý dữ liệu trả về
      $('#quantity_' + cartItemId).val(response);
    },
    error: function (error) {
      console.log('Error:', error);
    }
  });
}

function giamCart(cartItemId) {
  $.ajax({
    type: 'POST',
    url: '/giamcart', // Đường dẫn đến controller
    data: {
      cartItemId: cartItemId
    },
    success: function (response) {
      // Xử lý dữ liệu trả về nếu cần
      console.log(response);
      // Không cần làm gì nếu không muốn xử lý dữ liệu trả về
      $('#quantity_' + cartItemId).val(response);
    },
    error: function (error) {
      console.log('Error:', error);
    }
  });
}
