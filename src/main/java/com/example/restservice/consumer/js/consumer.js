const { interval, Observable } = rxjs;
const { map, filter } = rxjs.operators;
const RxHR = rhr.RxHR;
let URL = "http://localhost:8080/screenshot"

const interval_html = document.getElementById('interval')
const submit_button = document.getElementById('submit')
const image_html = document.getElementById("image")

let sub;
let counter=0;

Rx.Observable.fromEvent(submit_button, 'click').subscribe(() => {
    counter++;
    if (counter%2 != 0) {
        submit_button.value = "STOP";
        const observable = interval(interval_html.value * 1000);
        const observable2 = observable.pipe(
            map(() => RxHR.get(URL).subscribe(data => {
                image_html.src = ("data:image/gif;base64," + data.body);
                
            })),
        );
        sub = observable2.subscribe();
    } else {
        submit_button.value = "START"
        sub.unsubscribe();
    }

})