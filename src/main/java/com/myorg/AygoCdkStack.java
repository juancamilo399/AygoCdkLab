package com.myorg;

import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.iam.IRole;
import software.amazon.awscdk.services.iam.Role;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class AygoCdkStack extends Stack {
    public AygoCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AygoCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        IVpc vpc = Vpc.fromLookup(this,"vpc-040e8f55fe8e59608", VpcLookupOptions.builder().isDefault(Boolean.TRUE).build());
        SecurityGroup securityGroup = SecurityGroup.Builder.create(this,"sg_aygo")
                .vpc(vpc)
                .securityGroupName("sg_aygo")
                .build();

        securityGroup.addIngressRule(
                Peer.anyIpv4(),
                Port.tcp(22),
                "Allows SSH access from Internet"
        );

        securityGroup.addIngressRule(
                Peer.anyIpv4(),
                Port.tcp(80),
                "Allows HTTP access from Internet"
        );

        securityGroup.addIngressRule(
                Peer.anyIpv4(),
                Port.tcp(443),
                "Allows HTTPS access from Internet"
        );
        securityGroup.addIngressRule(
                Peer.anyIpv4(),
                Port.tcp(42000),
                "Allows docker port access"
        );

        IRole role = Role.fromRoleArn(this,"LabRole ","arn:aws:iam::900101194060:role/LabRole");

        UserData userData = UserData.forLinux();

        userData.addCommands("sudo yum update -y");
        userData.addCommands("sudo yum -y install docker");
        userData.addCommands("sudo service docker start");
        userData.addCommands("sudo usermod -a -G docker ec2-user");
        userData.addCommands("docker pull juancamilo399/sparkwebaygo");
        userData.addCommands("docker run -d -p 42000:6000 --name firstdockerimageaws juancamilo399/sparkwebaygo");


        Instance instance = Instance.Builder.create(this,"aygo_ec2")
                .instanceName("aygo_ec2")
                .vpc(vpc)
                .securityGroup(securityGroup)
                .role(role)
                .instanceType(InstanceType.of(InstanceClass.T2,InstanceSize.MICRO))
                .machineImage(MachineImage.latestAmazonLinux2())
                .keyName("aygo_key")
                .userData(userData)
                .build();
    }
}
