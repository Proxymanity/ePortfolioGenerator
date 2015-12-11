	
 $(document).ready(function() {
            window.onload=(function(event){
               $.getJSON('json/' + SiteName, function(jd) {
                var pages = jd.pages;	//Now is array of pages
				var items = pages[pageNumber].ITEMS;
				var headers = pages[pageNumber].HEADER;
				var texts = pages[pageNumber].TEXT;
				var lists = pages[pageNumber].LIST;
				var images = pages[pageNumber].IMAGE;
				var videos = pages[pageNumber].VIDEO;
				  $('#Title').html(pages[pageNumber].title);	
					$('#layout').attr("href", "css/layout" + pages[pageNumber].layout + ".css");
					$('#font').attr("href", "css/font" + pages[pageNumber].font + ".css");
					$('#color').attr("href", "css/color" + pages[pageNumber].color + ".css");
					$('#Banner').html(jd.studentName);
					$('#footer').html(pages[pageNumber].footer);
					$('#BannerI').attr("src", "img/" + pages[pageNumber].bannerImageName);
					
					var i;
					var NavagationItems = "";
					for(i = 0; i < pages.length; i++){
						NavagationItems += ("<li><a href=" + pages[i].title+".html>"+
									pages[i].title +  "</li>");
					}
					$('#NavagationList').html(NavagationItems);
					
					var textCounter = 0;
					var imageCounter = 0;
					var videoCounter = 0;
					var headerCounter = 0;
					var listCounter = 0;
        
					var listItems;
					var x;
					var content = ""; //Holds the content
					
					for(i = 0; i < items.length; i++){
						if( items[i].valueOf() == "HEADER"){
							content += "<h3 class ='content' id = 'header'>";
							content += headers[headerCounter].text;
							content += "</h3>";
							headerCounter++;
						}else if(( items[i].valueOf() == "TEXT")){
							content += "<p class ='content' id = 'text'>";
							content += texts[textCounter].text;
							content += "</p>";
							textCounter++;
						}else if(( items[i].valueOf() == "IMAGE")){
							content += "<div class = 'content' id = 'image'> <img width ='";
							content += images[imageCounter].width;
							content += "' height = '" + images[imageCounter].height + "' src =";
							content += "'img/" + images[imageCounter].ImageName + "' align =";
							content += "'" + images[imageCounter].alignment + "'";
							content += "><div> " + images[imageCounter].caption;
							content += "</div></div>";
							imageCounter++;
						}else if(( items[i].valueOf() == "VIDEO")){
							content += "<video class = 'content' ";
							content += "width ='" + videos[videoCounter].width + "'";
							content += "height ='" + videos[videoCounter].height + "' controls>";
							content += "<source src='video/" + videos[videoCounter].VideoName + "' ></video>"
							videoCounter++;
							
						}else if(items[i].valueOf() == "LIST"){
							listItems = lists[listCounter].listItems;
							content += "<ul class = 'content'>"
							
							for(x = 0; x < listItems.length; x++){
								content += "<li>" + listItems[x] + "</li>";
							}
							
							content += "</ul>";
							listCounter++;
						}
					}
					$('#contentMarker').html(content);
						
               });
            });
         });
 