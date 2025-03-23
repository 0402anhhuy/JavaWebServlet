$(document).ready(function() {
    $('.btn-delete').click(function (){
        const id = $(this).attr("id")
        const row = $(this).closest('tr')
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/Project_war/roles",
            data: { id: id }
        }).done(function( msg ) {
            if(msg){
                row.remove()
            }
            else{
                alert("Xóa thất bại")
            }
        });
    })
})