(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{17:function(t,e,n){},18:function(t,e,n){},19:function(t,e,n){"use strict";n.r(e);var i=n(0),o=n.n(i),r=n(5),a=n.n(r),l=(n(17),n(3)),u=(n(18),n(8)),s=n(2),c=n(1);function h(t,e){var n="undefined"!==typeof Symbol&&t[Symbol.iterator]||t["@@iterator"];if(!n){if(Array.isArray(t)||(n=function(t,e){if(!t)return;if("string"===typeof t)return f(t,e);var n=Object.prototype.toString.call(t).slice(8,-1);"Object"===n&&t.constructor&&(n=t.constructor.name);if("Map"===n||"Set"===n)return Array.from(t);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return f(t,e)}(t))||e&&t&&"number"===typeof t.length){n&&(t=n);var i=0,o=function(){};return{s:o,n:function(){return i>=t.length?{done:!0}:{done:!1,value:t[i++]}},e:function(t){throw t},f:o}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var r,a=!0,l=!1;return{s:function(){n=n.call(t)},n:function(){var t=n.next();return a=t.done,t},e:function(t){l=!0,r=t},f:function(){try{a||null==n.return||n.return()}finally{if(l)throw r}}}}function f(t,e){(null==e||e>t.length)&&(e=t.length);for(var n=0,i=new Array(e);n<e;n++)i[n]=t[n];return i}function d(){d=function(){return t};var t={},e=Object.prototype,n=e.hasOwnProperty,i="function"==typeof Symbol?Symbol:{},o=i.iterator||"@@iterator",r=i.asyncIterator||"@@asyncIterator",a=i.toStringTag||"@@toStringTag";function l(t,e,n){return Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}),t[e]}try{l({},"")}catch(S){l=function(t,e,n){return t[e]=n}}function u(t,e,n,i){var o=e&&e.prototype instanceof h?e:h,r=Object.create(o.prototype),a=new C(i||[]);return r._invoke=function(t,e,n){var i="suspendedStart";return function(o,r){if("executing"===i)throw new Error("Generator is already running");if("completed"===i){if("throw"===o)throw r;return O()}for(n.method=o,n.arg=r;;){var a=n.delegate;if(a){var l=k(a,n);if(l){if(l===c)continue;return l}}if("next"===n.method)n.sent=n._sent=n.arg;else if("throw"===n.method){if("suspendedStart"===i)throw i="completed",n.arg;n.dispatchException(n.arg)}else"return"===n.method&&n.abrupt("return",n.arg);i="executing";var u=s(t,e,n);if("normal"===u.type){if(i=n.done?"completed":"suspendedYield",u.arg===c)continue;return{value:u.arg,done:n.done}}"throw"===u.type&&(i="completed",n.method="throw",n.arg=u.arg)}}}(t,n,a),r}function s(t,e,n){try{return{type:"normal",arg:t.call(e,n)}}catch(S){return{type:"throw",arg:S}}}t.wrap=u;var c={};function h(){}function f(){}function p(){}var v={};l(v,o,function(){return this});var w=Object.getPrototypeOf,m=w&&w(w(E([])));m&&m!==e&&n.call(m,o)&&(v=m);var y=p.prototype=h.prototype=Object.create(v);function b(t){["next","throw","return"].forEach(function(e){l(t,e,function(t){return this._invoke(e,t)})})}function g(t,e){var i;this._invoke=function(o,r){function a(){return new e(function(i,a){!function i(o,r,a,l){var u=s(t[o],t,r);if("throw"!==u.type){var c=u.arg,h=c.value;return h&&"object"==typeof h&&n.call(h,"__await")?e.resolve(h.__await).then(function(t){i("next",t,a,l)},function(t){i("throw",t,a,l)}):e.resolve(h).then(function(t){c.value=t,a(c)},function(t){return i("throw",t,a,l)})}l(u.arg)}(o,r,i,a)})}return i=i?i.then(a,a):a()}}function k(t,e){var n=t.iterator[e.method];if(void 0===n){if(e.delegate=null,"throw"===e.method){if(t.iterator.return&&(e.method="return",e.arg=void 0,k(t,e),"throw"===e.method))return c;e.method="throw",e.arg=new TypeError("The iterator does not provide a 'throw' method")}return c}var i=s(n,t.iterator,e.arg);if("throw"===i.type)return e.method="throw",e.arg=i.arg,e.delegate=null,c;var o=i.arg;return o?o.done?(e[t.resultName]=o.value,e.next=t.nextLoc,"return"!==e.method&&(e.method="next",e.arg=void 0),e.delegate=null,c):o:(e.method="throw",e.arg=new TypeError("iterator result is not an object"),e.delegate=null,c)}function x(t){var e={tryLoc:t[0]};1 in t&&(e.catchLoc=t[1]),2 in t&&(e.finallyLoc=t[2],e.afterLoc=t[3]),this.tryEntries.push(e)}function z(t){var e=t.completion||{};e.type="normal",delete e.arg,t.completion=e}function C(t){this.tryEntries=[{tryLoc:"root"}],t.forEach(x,this),this.reset(!0)}function E(t){if(t){var e=t[o];if(e)return e.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length)){var i=-1,r=function e(){for(;++i<t.length;)if(n.call(t,i))return e.value=t[i],e.done=!1,e;return e.value=void 0,e.done=!0,e};return r.next=r}}return{next:O}}function O(){return{value:void 0,done:!0}}return f.prototype=p,l(y,"constructor",p),l(p,"constructor",f),f.displayName=l(p,a,"GeneratorFunction"),t.isGeneratorFunction=function(t){var e="function"==typeof t&&t.constructor;return!!e&&(e===f||"GeneratorFunction"===(e.displayName||e.name))},t.mark=function(t){return Object.setPrototypeOf?Object.setPrototypeOf(t,p):(t.__proto__=p,l(t,a,"GeneratorFunction")),t.prototype=Object.create(y),t},t.awrap=function(t){return{__await:t}},b(g.prototype),l(g.prototype,r,function(){return this}),t.AsyncIterator=g,t.async=function(e,n,i,o,r){void 0===r&&(r=Promise);var a=new g(u(e,n,i,o),r);return t.isGeneratorFunction(n)?a:a.next().then(function(t){return t.done?t.value:a.next()})},b(y),l(y,a,"Generator"),l(y,o,function(){return this}),l(y,"toString",function(){return"[object Generator]"}),t.keys=function(t){var e=[];for(var n in t)e.push(n);return e.reverse(),function n(){for(;e.length;){var i=e.pop();if(i in t)return n.value=i,n.done=!1,n}return n.done=!0,n}},t.values=E,C.prototype={constructor:C,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=void 0,this.done=!1,this.delegate=null,this.method="next",this.arg=void 0,this.tryEntries.forEach(z),!t)for(var e in this)"t"===e.charAt(0)&&n.call(this,e)&&!isNaN(+e.slice(1))&&(this[e]=void 0)},stop:function(){this.done=!0;var t=this.tryEntries[0].completion;if("throw"===t.type)throw t.arg;return this.rval},dispatchException:function(t){if(this.done)throw t;var e=this;function i(n,i){return a.type="throw",a.arg=t,e.next=n,i&&(e.method="next",e.arg=void 0),!!i}for(var o=this.tryEntries.length-1;o>=0;--o){var r=this.tryEntries[o],a=r.completion;if("root"===r.tryLoc)return i("end");if(r.tryLoc<=this.prev){var l=n.call(r,"catchLoc"),u=n.call(r,"finallyLoc");if(l&&u){if(this.prev<r.catchLoc)return i(r.catchLoc,!0);if(this.prev<r.finallyLoc)return i(r.finallyLoc)}else if(l){if(this.prev<r.catchLoc)return i(r.catchLoc,!0)}else{if(!u)throw new Error("try statement without catch or finally");if(this.prev<r.finallyLoc)return i(r.finallyLoc)}}}},abrupt:function(t,e){for(var i=this.tryEntries.length-1;i>=0;--i){var o=this.tryEntries[i];if(o.tryLoc<=this.prev&&n.call(o,"finallyLoc")&&this.prev<o.finallyLoc){var r=o;break}}r&&("break"===t||"continue"===t)&&r.tryLoc<=e&&e<=r.finallyLoc&&(r=null);var a=r?r.completion:{};return a.type=t,a.arg=e,r?(this.method="next",this.next=r.finallyLoc,c):this.complete(a)},complete:function(t,e){if("throw"===t.type)throw t.arg;return"break"===t.type||"continue"===t.type?this.next=t.arg:"return"===t.type?(this.rval=this.arg=t.arg,this.method="return",this.next="end"):"normal"===t.type&&e&&(this.next=e),c},finish:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var n=this.tryEntries[e];if(n.finallyLoc===t)return this.complete(n.completion,n.afterLoc),z(n),c}},catch:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var n=this.tryEntries[e];if(n.tryLoc===t){var i=n.completion;if("throw"===i.type){var o=i.arg;z(n)}return o}}throw new Error("illegal catch attempt")},delegateYield:function(t,e,n){return this.delegate={iterator:E(t),resultName:e,nextLoc:n},"next"===this.method&&(this.arg=void 0),c}},t}var p=function t(e,n){Object(c.a)(this,t),this.row=e,this.column=n},v=function(){function t(e,n,i){Object(c.a)(this,t),this.deltar=e,this.deltac=n,this.label=i}return Object(s.a)(t,null,[{key:"parse",value:function(t){return"down"===t||"Down"===t?w:"up"===t||"Up"===t?m:"left"===t||"Left"===t?y:"right"===t||"Right"===t?b:g}}]),t}(),w=new v(1,0,"down"),m=new v(-1,0,"up"),y=new v(0,-1,"left"),b=new v(0,1,"right"),g=new v(0,0,"*"),k=function(){function t(e,n,i,o){Object(c.a)(this,t),this.width=e,this.height=n,this.isWinner=i,this.row=0,this.column=0,this.label=o}return Object(s.a)(t,[{key:"place",value:function(t,e){this.row=t,this.column=e}},{key:"move",value:function(t){this.row+=t.deltar,this.column+=t.deltac}},{key:"equals",value:function(e){return e instanceof t&&(this.height===e.height&&this.width===e.width&&this.row===e.row&&this.column===e.column)}},{key:"location",value:function(){return new p(this.row,this.column)}},{key:"key",value:function(){return""+this.width+this.height}},{key:"coordinates",value:d().mark(function t(){var e,n;return d().wrap(function(t){for(;;)switch(t.prev=t.next){case 0:e=0;case 1:if(!(e<this.height)){t.next=12;break}n=0;case 3:if(!(n<this.width)){t.next=9;break}return t.next=6,new p(this.row+e,this.column+n);case 6:n++,t.next=3;break;case 9:e++,t.next=1;break;case 12:case"end":return t.stop()}},t,this)})},{key:"contains",value:function(t){var e,n=h(Object(u.a)(this.coordinates()));try{for(n.s();!(e=n.n()).done;){var i=e.value;if(i.row===t.row&&i.column===t.column)return!0}}catch(o){n.e(o)}finally{n.f()}return!1}},{key:"copy",value:function(){var e=new t(this.width,this.height,this.isWinner,this.label);return e.place(this.row,this.column),e}}]),t}(),x=function(){function t(e,n,i,o,r){Object(c.a)(this,t),this.numRows=e,this.numColumns=n,this.destination=i,this.finalMove=o,this.exit=r,this.selected=null}return Object(s.a)(t,[{key:"initialize",value:function(t){this.pieces=t.map(function(t){return t.copy()})}},{key:"hasWon",value:function(){var t=this.pieces.findIndex(function(t){return t.isWinner});return this.destination.row===this.pieces[t].row&&this.destination.column===this.pieces[t].column}},{key:"blocks",value:d().mark(function t(){var e;return d().wrap(function(t){for(;;)switch(t.prev=t.next){case 0:e=0;case 1:if(!(e<this.pieces.length)){t.next=7;break}return t.next=4,this.pieces[e];case 4:e++,t.next=1;break;case 7:case"end":return t.stop()}},t,this)})},{key:"clone",value:function(){var e=new t(this.numRows,this.numColumns,this.destination,this.finalMove,this.exit);e.pieces=[];var n,i=h(this.pieces);try{for(i.s();!(n=i.n()).done;){var o=n.value,r=o.copy();e.pieces.push(r),o===this.selected&&(e.selected=r)}}catch(a){i.e(a)}finally{i.f()}return e}},{key:"select",value:function(t){this.selected=t}},{key:"isSelected",value:function(t){return t===this.selected}},{key:"isCovered",value:function(t){return this.pieces.findIndex(function(e){return e.contains(t)})>=0}},{key:"key",value:function(){for(var t=this,e="",n=0;n<this.numRows;n++){for(var i=function(i){var o=new p(n,i),r=t.pieces.findIndex(function(t){return t.contains(o)});e+=r<0?"00":t.pieces[r].key()},o=0;o<this.numColumns;o++)i(o);e+="\n"}return e}},{key:"availableMoves",value:function(){var t=this.selected;if(null==t)return[];var e=[],n=this.selected.location(),i=!1;if(n.column>0){i=!0;for(var o=0;o<t.height;o++)if(this.isCovered(new p(n.row+o,n.column-1))){i=!1;break}}if(i&&e.push(y),n.column+t.width<this.numColumns){i=!0;for(var r=0;r<t.height;r++)if(this.isCovered(new p(n.row+r,n.column+t.width))){i=!1;break}i&&e.push(b)}if(n.row>0){i=!0;for(var a=0;a<t.width;a++)if(this.isCovered(new p(n.row-1,n.column+a))){i=!1;break}i&&e.push(m)}if(n.row+t.height<this.numRows){i=!0;for(var l=0;l<t.width;l++)if(this.isCovered(new p(n.row+t.height,n.column+l))){i=!1;break}i&&e.push(w)}return e}}]),t}(),z=function(){function t(e){Object(c.a)(this,t),this.id=t._id,t._id+=1,"undefined"!==typeof e&&this.initialize(e)}return Object(s.a)(t,[{key:"initialize",value:function(t){var e,n=v.parse(t.board.finalMove),i=parseInt(t.board.rows),o=parseInt(t.board.columns),r=new p(parseInt(t.board.destination.row),parseInt(t.board.destination.column)),a=[parseInt(t.board.exit.start),parseInt(t.board.exit.end)],l=[],u=h(t.pieces);try{for(u.s();!(e=u.n()).done;){var s=e.value;l.push(new k(parseInt(s.width),parseInt(s.height),"true"===s.isWinner,s.label))}}catch(w){u.e(w)}finally{u.f()}var c,f=h(t.locations);try{var d=function(){var t=c.value,e=new p(parseInt(t.location.row),parseInt(t.location.column)),n=l.findIndex(function(e){return e.label===t.piece});l[n].place(e.row,e.column)};for(f.s();!(c=f.n()).done;)d()}catch(w){f.e(w)}finally{f.f()}this.puzzle=new x(i,o,r,n,a),this.puzzle.initialize(l),this.numMoves=0,this.showLabels=!1,this.victory=!1}},{key:"updateMoveCount",value:function(t){this.numMoves+=t}},{key:"setShowLabels",value:function(t){this.showLabels=t}},{key:"numberMoves",value:function(){return this.numMoves}},{key:"victorious",value:function(){this.victory=!0}},{key:"isVictorious",value:function(){return this.victory}},{key:"available",value:function(t){if(!this.puzzle.selected)return!1;if(t===g)return!1;var e=this.puzzle.availableMoves();return!(!this.puzzle.selected.isWinner||this.puzzle.selected.row!==this.puzzle.destination.row||this.puzzle.selected.column!==this.puzzle.destination.column||this.puzzle.finalMove!==t)||e.includes(t)}},{key:"copy",value:function(){var e=new t;return e.puzzle=this.puzzle.clone(),e.numMoves=this.numMoves,e.showLabels=this.showLabels,e.victory=this.victory,e}}]),t}();z._id=0;var C=100,E=8,O=function(){function t(e,n,i,o){Object(c.a)(this,t),this.x=e,this.y=n,this.width=i,this.height=o}return Object(s.a)(t,[{key:"contains",value:function(t,e){return t>=this.x&&t<=this.x+this.width&&e>=this.y&&e<=this.y+this.height}}]),t}();function S(t){var e=t.location();return new O(C*e.column+E,C*e.row+E,C*t.width-2*E,C*t.height-2*E)}function j(t,e,n){var i=e.getContext("2d");i.clearRect(0,0,e.width,e.height);var o=t.puzzle,r=o.numRows;if(o.numColumns>r&&(r=o.numColumns),C=e.width/r,o.numRows!==r?(i.fillStyle=window.getComputedStyle(n).backgroundColor,i.fillRect(0,C*o.numRows,e.width,C*(r-o.numRows))):o.numColumns!==r&&(i.fillStyle=window.getComputedStyle(n).backgroundColor,i.fillRect(C*o.numColumns,0,C*(r-o.numColumns),e.height)),t.puzzle){if(function(t,e,n){t.shadowColor="black";var i=e.selected;e.pieces.forEach(function(e){var o=S(e);e===i?t.fillStyle="yellow":e.isWinner?t.fillStyle="red":t.fillStyle="lightblue",t.shadowBlur=10,t.fillRect(o.x,o.y,o.width,o.height),n&&(t.font="24px Consolas",t.fillStyle="black",t.shadowBlur=0,t.fillText(e.label,o.x+o.width/2-6,o.y+o.height/2+6))})}(i,t.puzzle,t.showLabels),i.fillStyle="brown",i.shadowBlur=0,o.finalMove===y){var a=o.exit;i.fillRect(0,0,E,a[0]*C),i.fillRect(0,(a[1]+1)*C,E,(o.numRows-a[0])*C)}else i.fillRect(0,0,E,o.numRows*C);if(o.finalMove===b){var l=o.exit;i.fillRect(o.numColumns*C-E,0,E,l[0]*C),i.fillRect(o.numColumns*C-E,(l[1]+1)*C,E,(o.numRows-l[0])*C)}else i.fillRect(o.numColumns*C-E,0,E,o.numRows*C);if(o.finalMove===w){var u=o.exit;i.fillRect(0,o.numRows*C-E,(u[1]-1)*C,E),i.fillRect((u[1]+1)*C,o.numRows*C-E,(o.numColumns-u[1]-1)*C,E)}else i.fillRect(0,o.numRows*C-E,o.numColumns*C,E);if(o.finalMove===m){var s=o.exit;i.fillRect(0,0,(s[1]-1)*C,E),i.fillRect((s[1]+1)*C,0,(s[1]-s[0])*C,E)}else i.fillRect(0,0,o.numColumns*C,E)}}function L(t,e){var n="undefined"!==typeof Symbol&&t[Symbol.iterator]||t["@@iterator"];if(!n){if(Array.isArray(t)||(n=function(t,e){if(!t)return;if("string"===typeof t)return R(t,e);var n=Object.prototype.toString.call(t).slice(8,-1);"Object"===n&&t.constructor&&(n=t.constructor.name);if("Map"===n||"Set"===n)return Array.from(t);if("Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return R(t,e)}(t))||e&&t&&"number"===typeof t.length){n&&(t=n);var i=0,o=function(){};return{s:o,n:function(){return i>=t.length?{done:!0}:{done:!1,value:t[i++]}},e:function(t){throw t},f:o}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var r,a=!0,l=!1;return{s:function(){n=n.call(t)},n:function(){var t=n.next();return a=t.done,t},e:function(t){l=!0,r=t},f:function(){try{a||null==n.return||n.return()}finally{if(l)throw r}}}}function R(t,e){(null==e||e>t.length)&&(e=t.length);for(var n=0,i=new Array(e);n<e;n++)i[n]=t[n];return i}var I=function t(e,n,i){Object(c.a)(this,t),this.board=e,this.previous=n,this.direction=i},M=function t(e){Object(c.a)(this,t),this.thing=e,this.next=null},W=function(){function t(){Object(c.a)(this,t),this.head=null,this.tail=null}return Object(s.a)(t,[{key:"isEmpty",value:function(){return null==this.head}},{key:"enqueue",value:function(t){null==this.head?(this.head=new M(t),this.tail=this.head):(this.tail.next=new M(t),this.tail=this.tail.next)}},{key:"dequeue",value:function(){if(this.head===this.tail){var t=this.head.thing;return this.head=this.tail=null,t}var e=this.head.thing;return this.head=this.head.next,e}}]),t}();var A={canvas:{height:"500",width:"500"},Appmain:{backgroundColor:"#320453",height:"100vh",width:"100vw"},buttons:{position:"absolute",left:150,top:450},upbutton:{position:"absolute",left:50,top:80},downbutton:{position:"absolute",left:50,top:120},leftbutton:{position:"absolute",top:100},rightbutton:{position:"absolute",top:100,left:100},resetbutton:{position:"absolute",top:70,left:175},solvebutton:{position:"absolute",top:100,left:175},loadbutton:{position:"absolute",top:130,left:175},solution:{position:"absolute",top:600,left:20,numRows:5},inputPuzzle:{position:"absolute",top:500,left:520},inputPuzzleChange:{position:"absolute",top:500,left:750}},_={canvas:{height:"500",width:"500"},Appmain:{backgroundColor:"#320453",height:"100vh",width:"100vw"},buttons:{position:"absolute",left:550,top:150},upbutton:{position:"absolute",left:50,top:80},downbutton:{position:"absolute",left:50,top:120},leftbutton:{position:"absolute",top:100},rightbutton:{position:"absolute",top:100,left:100},resetbutton:{position:"absolute",top:180,left:35},solvebutton:{position:"absolute",top:220,left:35},loadbutton:{position:"absolute",top:260,left:35},solution:{position:"absolute",top:20,left:550,numRows:10},inputPuzzle:{position:"absolute",top:500,left:520},inputPuzzleChange:{position:"absolute",top:500,left:750}},N=n(6),P=n.n(N),G=n(7),B=JSON.parse(JSON.stringify({name:"WoodPuzzle 4x5",board:{rows:"5",columns:"4",target:"B",destination:{row:"3",column:"1"},exit:{start:"1",end:"2"},finalMove:"Down"},pieces:[{label:"A",isWinner:"false",width:"1",height:"2"},{label:"B",isWinner:"true",width:"2",height:"2"},{label:"C",isWinner:"false",width:"1",height:"2"},{label:"D",isWinner:"false",width:"1",height:"2"},{label:"E",isWinner:"false",width:"1",height:"1"},{label:"F",isWinner:"false",width:"1",height:"1"},{label:"G",isWinner:"false",width:"1",height:"2"},{label:"H",isWinner:"false",width:"1",height:"1"},{label:"I",isWinner:"false",width:"1",height:"1"},{label:"J",isWinner:"false",width:"2",height:"1"}],locations:[{piece:"A",location:{row:"0",column:"0"}},{piece:"B",location:{row:"0",column:"1"}},{piece:"C",location:{row:"0",column:"3"}},{piece:"D",location:{row:"2",column:"0"}},{piece:"E",location:{row:"2",column:"1"}},{piece:"F",location:{row:"2",column:"2"}},{piece:"G",location:{row:"2",column:"3"}},{piece:"H",location:{row:"3",column:"1"}},{piece:"I",location:{row:"3",column:"2"}},{piece:"J",location:{row:"4",column:"1"}}]})),J=!1;var T=function(){var t=o.a.useState(new z(B)),e=Object(l.a)(t,2),n=e[0],i=e[1],r=o.a.useState(!1),a=Object(l.a)(r,2),u=a[0],s=a[1],c=o.a.useState(!1),h=Object(l.a)(c,2),f=h[0],d=h[1],p=o.a.useState(""),v=Object(l.a)(p,2),k=v[0],x=v[1],C=o.a.useState(!1),E=Object(l.a)(C,2),O=E[0],R=E[1],M=o.a.useState(""),N=Object(l.a)(M,2),T=N[0],q=N[1],F=o.a.useState({height:window.innerHeight,width:window.innerWidth}),D=Object(l.a)(F,2),H=(D[0],D[1]),U=Object(G.useMediaQuery)({query:"(min-width: 900px)"})?_:A,Y=o.a.useRef(null),$=o.a.useRef(null);o.a.useEffect(function(){document.title="Wood Puzzle",window.addEventListener("resize",function(){H({height:window.innerHeight,width:window.innerWidth})}),j(n,$.current,Y.current)},[n]);var K=function(t){var e=function(t,e){var n=t.puzzle.selected;return n?(t.puzzle.hasWon()&&e===t.puzzle.finalMove?(t.puzzle.pieces=t.puzzle.pieces.filter(function(t){return t!==n}),t.puzzle.selected=null,t.victorious()):n.move(e),t.updateMoveCount(1),t.copy()):t}(n,t);if(f){var o=k.indexOf("\n"),r=k.substring(o+1);x(r),0===r.length&&d(!1)}i(e)},V=function(){if(!f){var t=function(t){var e=new Map,n=new W,i=t.puzzle.clone(),o=new I(i,null,g);e.set(i.key(),!0),n.enqueue(o);for(var r=[m,w,y,b];!n.isEmpty();){o=n.dequeue();for(var a=0;a<o.board.pieces.length;a++){var l,u=L(r);try{for(u.s();!(l=u.n()).done;){var s=l.value,c=o.board.clone();if(c.selected=c.pieces[a],c.availableMoves().includes(s)){c.selected.move(s);var h=new I(c,o,s);if(c.hasWon()){for(var f="";null!=h.previous;)f=h.board.selected.label+" "+h.direction.label+"\n"+f,h=h.previous;return f}var d=c.key();!0===e.get(d)||(e.set(d,!0),n.enqueue(h))}}}catch(p){u.e(p)}finally{u.f()}}}return"*No Solution*"}(n);x(t)}d(!f)};return o.a.createElement("main",{style:U.Appmain,ref:Y},o.a.createElement("canvas",{tabIndex:"1",className:"App-canvas",ref:$,width:U.canvas.width,height:U.canvas.height,onClick:function(t){var e=function(t,e,n){var i=e.getBoundingClientRect(),o=t.puzzle.pieces.findIndex(function(t){return S(t).contains(n.clientX-i.left,n.clientY-i.top)}),r=null;return o>=0&&(r=t.puzzle.pieces[o]),t.puzzle.select(r),t.copy()}(n,$.current,t);i(e)},onKeyDown:function(t){if(!J){J=!0;var e=null;37===t.keyCode&&n.available(y)?e=y:38===t.keyCode&&n.available(m)?e=m:39===t.keyCode&&n.available(b)?e=b:40===t.keyCode&&n.available(w)&&(e=w),e&&K(e)}},onKeyUp:function(t){J=!1}}),n.isVictorious()?o.a.createElement("img",{src:P.a,alt:"fireworks"}):null,o.a.createElement("p",{className:"nummoves"},"#Moves: ",n.numberMoves()),o.a.createElement("label",{className:"showlabels"},o.a.createElement("input",{type:"checkbox",checked:u,onChange:function(){s(!u),n.setShowLabels(!u),i(n.copy())}}),"Show Labels"),o.a.createElement("textarea",{style:U.solution,value:k,rows:U.solution.numRows,cols:10,hidden:!f,readOnly:!0}),o.a.createElement("textarea",{style:U.inputPuzzle,placeholder:"Enter JSON here",rows:5,onChange:function(t){q(t.target.value)},hidden:!O}),O?o.a.createElement("button",{style:U.inputPuzzleChange,onClick:function(t){R(!O),B=JSON.parse(T);try{var e=new z(B);i(e),s(!1)}catch(n){console.log("Problem parsing input:"+n)}},hidden:O},"Change Puzzle"):null,o.a.createElement("div",{style:U.buttons},o.a.createElement("button",{style:U.upbutton,onClick:function(t){return K(m)},disabled:!n.available(m)},"^"),o.a.createElement("button",{style:U.leftbutton,onClick:function(t){return K(y)},disabled:!n.available(y)},"<"),o.a.createElement("button",{style:U.rightbutton,onClick:function(t){return K(b)},disabled:!n.available(b)},">"),o.a.createElement("button",{style:U.downbutton,onClick:function(t){return K(w)},disabled:!n.available(w)},"v"),o.a.createElement("button",{style:U.resetbutton,onClick:function(t){return function(){var t=new z(B);i(t),s(!1)}()}},"Reset"),o.a.createElement("button",{style:U.solvebutton,onClick:function(t){return V()}},"Solve"),o.a.createElement("button",{style:U.loadbutton,onClick:function(){R(!O)}},"Load\xa0")))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));a.a.render(o.a.createElement(T,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(t){t.unregister()}).catch(function(t){console.error(t.message)})},6:function(t,e,n){t.exports=n.p+"static/media/fireworks.3f1d9e8c.gif"},9:function(t,e,n){t.exports=n(19)}},[[9,1,2]]]);
//# sourceMappingURL=main.c239c3c4.chunk.js.map