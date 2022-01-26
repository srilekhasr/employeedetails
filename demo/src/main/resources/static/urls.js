function create()
{
    
    window.location.replace("/employees/new")
}

function edit(id)
{
    window.location.replace("/employees/edit/"+id)
}

function Mydelete(id)
{
    alert("Employee successfully deleted:"+id)
    window.location.replace("/employees/"+id)
}