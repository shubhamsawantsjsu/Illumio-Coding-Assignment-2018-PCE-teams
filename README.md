# Illumio-Coding-Assignment-2018-PCE-teams

## Approach:

* I have created 4 separate objects for 'inbound-tcp', 'inbound-udp', 'outbound-tcp' and 'outbound-udp'. Each objects have their separate port-ip mapping maintained using HashMap.

* As I know, the max number of ports in case of tcp and udp is 65K. This is not a very large number to store. Hence I am storing all the ports along with ip_addresses associated to it.

* As I directly have access to ports associated to 4 defined objects, all that searching process happens in O(1), but the main task is searching ip_address.

* Due to time constraint I could not implement any better solution. I went with linear search. I have stored the ip addresses (range) associated with a port in a HashSet. First I do contains call, if it is present then no need to go further, time complexity is O(1). This is the best case, where entire search happens in O(1).

* But when contains call return false. I do linear search, going over each range and looking whether it contains input ip_address. The time complexity is O(number of ip ranges associated to port). Please pay attention here, I am not storing each ip address from the range, I am just storing the range. Hence size doesn't exceed.

* I would have implemented a better approach if I had time.  

## Testing

* I tested the code by giving test_firewall.csv as an input which contain inputs as well as expected output. If code output and expected output matches for all testcases then the message come 'All test cases passed successfully!!!!', else the code breaks, printing the failing test case.

## Policy team

* In my current semester, I am working on partition tolerance and eventual consistency in MongoDB, Redis caching, Vector Stamps. I am implementing all these features in my project. All these things closely match with what policy team is doing. I think, I will learn a lot if given a chance.  
