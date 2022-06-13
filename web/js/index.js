function delPhone(name){
    if(confirm('是否确认删除？')){
        window.location.href='phone.do?name=' + name + '&operate=del';
    }
}

function page(page){
    window.location.href="phone.do?page=" + page;
}

