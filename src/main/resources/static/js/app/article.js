var article = {
  init : function(){
    var _this = this;

    const createBtn = document.querySelector('#article-create-btn');
    const updateBtn = document.querySelector('#article-update-btn');
    const destroyBtn = document.querySelector('#article-destroy-btn');

    if(createBtn){
      createBtn.addEventListener('click', _this.create);
    }

    if(updateBtn){
      updateBtn.addEventListener('click', _this.update);
    }

    if(destroyBtn){
      destroyBtn.addEventListener('click', _this.destroy);
    }

  },

  //Article 생성
  create : function(){
    var data = {
      title: document.querySelector('#article-title').value,
      content: document.querySelector('#article-content').value,
    };

    fetch('/api/articles/',
    { method: 'POST', body: JSON.stringify(data), headers: {'Content-Type': 'application/json'} }
    ).then( function(response) {

      if(response.ok){
        alert('게시글 작성 성공');
        window.location.href='/articles';
      }else{
          alert('게시글 작성 실패');
      }

    });

  },

  //Article 수정
  update: function(){
    var data = {
      id: document.querySelector('#article-id').value,
      title: document.querySelector('#article-title').value,
      content: document.querySelector('#article-content').value,
    };

    fetch('/api/articles/' + data.id,
        { method: 'PUT', body: JSON.stringify(data), headers: {'Content-Type': 'application/json'} }
    ).then( function(response) {

      if(response.ok){
        alert('게시글 수정 성공');
        window.location.href = '/articles/' + data.id;
      }else{
        alert('게시글 수정 실패');
      }

    });

  },

  //Article 삭제
  destroy: function() {
    var split = location.pathname.split('/');
    var id = split[split.length - 1];

    fetch('/api/articles/' + id,
    {method: 'DELETE',}
    ).then(function(response){
      if(response.ok){
        alert('게시글 삭제함');
        window.location.href = "/articles";
      }else{
        alert('게시글 삭제 실패');
      }
    });

  }

};

article.init();
